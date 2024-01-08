package com.meche.api;

import com.meche.model.Charge;
import com.meche.service.ChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
