package com.meche.api;

import com.meche.api.operation.InventoryOperation;
import com.meche.api.operation.Utils;
import com.meche.model.*;
import com.meche.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.meche.model.enume.Status.PAID;
import static com.meche.model.enume.Status.PENDING;
import static com.meche.model.enume.TransactionType.SALE;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/hair/sale")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SaleApi {
    private final SaleService saleService;
    private final ProductService productService;
    private final InventoryOperation inventoryOperation;
    private final InvoiceSaleService invoiceSaleService;
    private final TransactionService transactionService;
    private final InventoryService inventoryService;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Sale>> getSales() throws InterruptedException {
        final List<Sale> sales = saleService.SALES();
        return new ResponseEntity<List<Sale>>(sales, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSale(@PathVariable("id") Long id) throws InterruptedException {
        Sale sale = saleService.getSale(id);
        TimeUnit.SECONDS.sleep(1);
        return ResponseEntity.ok(sale);
    }

    /**
     * By Month and Year parameter
     */
    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<List<Sale>> getSaleByMonthAndYear(
            @PathVariable("month") String month, @PathVariable("year") String year)
            throws InterruptedException {
        List<Sale> byMonthAndYear = new ArrayList<>();
        if (month.equalsIgnoreCase("null")) {
            Month month1 = now().getMonth();
            byMonthAndYear = saleService.findByMonthAndYear(month1, year);
        } else {
            byMonthAndYear = saleService.findByMonthAndYear(Month.valueOf(month), year);
        }
        return new ResponseEntity<List<Sale>>(byMonthAndYear, OK);
    }

    /**
     * By Day and Month parameters.
     */
    @GetMapping("/day/{day}/{month}")
    public ResponseEntity<List<Sale>> getSaleByDayAndMonth(
            @PathVariable("day") int day, @PathVariable("month") Month month)
            throws InterruptedException {
        List<Sale> byDayAndMonth = saleService.findByDayAndMonth(day, month, Sort.by("day", "month"));
        return new ResponseEntity<List<Sale>>(byDayAndMonth, OK);
    }

    @PutMapping("/validSale")
    public ResponseEntity<Sale> ValidSale(@RequestBody Sale saleToValid) throws InterruptedException {
        Sale saleById = saleService.getSale(saleToValid.getId());

        if (saleById == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        saleToValid.setStatus(PAID);
        Sale validSale = saleService.updateSale(saleToValid);
        TimeUnit.SECONDS.sleep(2);
        return new ResponseEntity<>(validSale, CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<List<Sale>> addSale(@RequestBody List<Sale> saleToSave) throws InterruptedException {
        double total = 0;
        List<InvoiceSale> invoiceSales = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.INVENTORY_LIST();
        final List<Inventory> cmupForSale = inventoryOperation.cmupForSale("SALE", saleToSave, inventoryList);
        Customer customerById = customerService.getCustomerById(saleToSave.get(0).getCustomer().getId());
//        TODO: I will update this code soon.
//        calcul the amount of transaction.
        for (Sale sale : saleToSave) {
            total += sale.getAmount();
        }
//        Fill and save Transaction.
        Transaction transactionSaved = saveTransaction(saleToSave, total, customerById);
//        save inventory.
        List<Inventory> inventoriesSaved = inventoryService.saveSaleInventory(cmupForSale);
//        Fil and save Sale.
        for (Sale sale : saleToSave) {
            sale.setCreateAt(now());
            sale.setStatus(PENDING);
            sale.setDay(LocalDate.now().getDayOfMonth());
            sale.setMonth(LocalDate.now().getMonth());
            sale.setYear(String.valueOf(Year.now()));
            sale.setTransaction(transactionSaved);
        }
//        saved sale
        final List<Sale> saleSaved = saleService.saveSale(saleToSave);
        saveInvoiceSale(saleSaved, total, invoiceSales);
        return new ResponseEntity<>(saleSaved, CREATED);
    }

    @GetMapping("/month")
    public ResponseEntity<List<Sale>> getSaleByMonth()
            throws InterruptedException {
        List<Sale> byMonth = saleService.findByMonth(LocalDate.now().getMonth());
        return new ResponseEntity<List<Sale>>(byMonth, OK);
    }

    @GetMapping("/day")
    public ResponseEntity<List<Sale>> getSaleByDay()
            throws InterruptedException {
        List<Sale> byDay = saleService.findByDay(LocalDate.now().getDayOfMonth());
        return new ResponseEntity<List<Sale>>(byDay, OK);
    }


    private Transaction saveTransaction(List<Sale> saleToSave, double total, Customer customer) {
        var transaction = Transaction.builder()
                .amount(total)
                .id(null)
                .timestamp(LocalDateTime.now())
                .type(SALE)
                .sender(customer.getName())
                .receiver("UNIVERSAL MECHE")
                .trsansactionId(Utils.generateTransactionId())
                .build();
        Transaction transactionSaved = transactionService.save(transaction);
        return transactionSaved;
    }

    private void saveInvoiceSale(List<Sale> saleSaved, double total, List<InvoiceSale> invoiceSales) {
        String invoiceNumber = Utils.generateInvoiceNumber();
        int invoiceTotal = 0;
//  Fil invoice  properties and save.
        for (Sale sale : saleSaved) {
            invoiceTotal += sale.getAmount();
            var invoiceSale = InvoiceSale.builder()
                    .sale(sale)
                    .customer(sale.getCustomer())
                    .tax(0)
                    .subTotal(total)
                    .total(total)
                    .invoiceNumber(invoiceNumber)
                    .status(PENDING)
                    .createAt(now())
                    .day(LocalDate.now().getDayOfMonth())
                    .month(LocalDate.now().getMonth())
                    .year(String.valueOf(Year.now()))
                    .build();
            invoiceSales.add(invoiceSale);
        }
//        saved invoice
        invoiceSaleService.addInvoiceSale(invoiceSales);
    }
}