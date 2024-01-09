package com.meche.api;

import com.meche.model.Benefice;
import com.meche.model.Charge;
import com.meche.model.Purchase;
import com.meche.model.Sale;
import com.meche.service.ChargeService;
import com.meche.service.PurchaseService;
import com.meche.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("/api/v1/hair/charge")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RequiredArgsConstructor
public class ChargeApi {

    private final ChargeService chargeService;
    private final SaleService saleService;
    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Charge>> charges() {
        List<Charge> charges = chargeService.getCharges();
        return new ResponseEntity<List<Charge>>(charges,OK);
    }
    @PostMapping
    public ResponseEntity<Charge> saveCharge(@RequestBody Charge charge) {
        Charge savedCharge = chargeService.save(charge);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharge);
    }

    @PutMapping
    public ResponseEntity<Charge> updateCharge(@RequestBody Charge charge) {
        Charge updatedCharge = chargeService.update(charge);
        return new ResponseEntity<>(CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Charge>getCharge(@PathVariable("id")Long id) {
        return new ResponseEntity<>(chargeService.getCharge(id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharge(@PathVariable("id") Long id) {
        chargeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/monthWinner")
    public ResponseEntity<Benefice>monthWinner() {
        Month currentMonth = LocalDateTime.now().getMonth();
        List<Purchase> purchases = purchaseService.findByMonth(currentMonth);
        double purchaseAmount=0;
        for (Purchase purchase: purchases){
            if(purchase.getMonth() == currentMonth){
                purchaseAmount += purchase.getAmount();
            }
        }

        List<Sale> sales = saleService.findByMonth(currentMonth);
        double saleAmount=0;
        for (Sale sale: sales){
            if(sale.getMonth() == currentMonth){
                saleAmount += sale.getAmount();
            }
        }
        double chargeAmount = getChargeAmount();
        double depenceTotal = purchaseAmount + chargeAmount;
        double beneficeRealise = saleAmount - depenceTotal      ;
       var gainProbable = Benefice.builder()
               .charge(chargeAmount)
               .purchase(purchaseAmount)
               .sale(saleAmount)
               .potentialWinner(beneficeRealise)
               .build();
        return new ResponseEntity<>(gainProbable, OK);
    }

    private double getChargeAmount() {
        List<Charge> charges = chargeService.getCharges();

        double chargeAmount=0;
        for (Charge charge  : charges){
            double electricity = charge.getElectricity();
            double loyer = charge.getLoyer();
            double impot = charge.getImpot();
            double ration = charge.getRation();
            double totalSalary = charge.getTotalSalary();
            double transport = charge.getTransport();
            chargeAmount = electricity + loyer + impot+ration+transport+totalSalary;
        }
        return chargeAmount;
    }

}
