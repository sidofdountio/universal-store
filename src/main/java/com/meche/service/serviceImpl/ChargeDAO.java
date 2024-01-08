package com.meche.service.serviceImpl;

import com.meche.model.Charge;

import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface ChargeDAO {
    Charge save(Charge charge);

    Charge update(Charge charge);

    List<Charge> getCharges();
    Charge getCharge(Long id);

    void delete(Long id);

}
