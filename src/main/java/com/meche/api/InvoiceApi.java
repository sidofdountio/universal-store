package com.meche.api;

import com.meche.model.InvoiceSale;
import com.meche.service.InvoiceSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 31/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("/api/v1/hair/invoice")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
public class InvoiceApi {
    private final InvoiceSaleService invoiceSaleService;

    /**
     * By invoice number without passed parameter
     */
    @GetMapping
    public ResponseEntity<List<InvoiceSale>> getInvoiceNumber() throws InterruptedException {
        List<InvoiceSale> byInvoiceNumber = invoiceSaleService.findByInvoiceNumber();
        return new ResponseEntity<>(byInvoiceNumber, OK);
    }

    /**
     * By invoice number.
     */
    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<List<InvoiceSale>> getInvoiceNumber(@PathVariable("invoiceNumber") String invoicenumber)
            throws InterruptedException {
        List<InvoiceSale> byInvoiceNumber = invoiceSaleService.findByInvoiceNumber(invoicenumber);
        return new ResponseEntity<List<InvoiceSale>>(byInvoiceNumber, OK);
    }

    /**
     * By Month and Year parameter
     */
    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<List<InvoiceSale>> getInvoiceByMonthAndYear(
            @PathVariable("month") String month, @PathVariable("year")String year)
            throws InterruptedException {
        List<InvoiceSale> invoiceSaleByMonthAndYear = new ArrayList<>();
        if (month.equalsIgnoreCase("null")) {
            Month month1 = now().getMonth();
            invoiceSaleByMonthAndYear = invoiceSaleService.findByMonthAndYear(month1, year, Sort.by("month", "year"));
        } else {
            invoiceSaleByMonthAndYear = invoiceSaleService.findByMonthAndYear(Month.valueOf(month), year, Sort.by("month", "year"));
        }
        return new ResponseEntity<>(invoiceSaleByMonthAndYear, OK);
    }

    /**
     * By Day and Month parameters.
     */
    @GetMapping("/day/{day}/{month}")
    public ResponseEntity<List<InvoiceSale>> getInvoiceByDayAndMonth(
            @PathVariable("day")int day,@PathVariable ("month") Month month)
            throws InterruptedException {
        List<InvoiceSale> byDayAndMonth = invoiceSaleService.findByDayAndMonth(day, month, Sort.by("day", "month"));
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity<List<InvoiceSale>>(byDayAndMonth, OK);
    }



}
