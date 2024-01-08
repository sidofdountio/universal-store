package com.meche.service;

import com.meche.model.Charge;
import com.meche.repo.ChargeRepo;
import com.meche.service.serviceImpl.ChargeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ChargeService implements ChargeDAO {
    private final ChargeRepo chargeRepo;
    @Override
    public Charge save(Charge charge) {
        return chargeRepo.save(charge);
    }

    @Override
    public Charge update(Charge charge) {
        return chargeRepo.save(charge);
    }

    @Override
    public List<Charge> getCharges() {
        return chargeRepo.findAll();
    }

    @Override
    public Charge getCharge(Long id) {
        return chargeRepo.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        chargeRepo.deleteById(id);
    }
}
