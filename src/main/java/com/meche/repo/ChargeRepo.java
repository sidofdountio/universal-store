package com.meche.repo;

import com.meche.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface ChargeRepo extends JpaRepository<Charge,Long> {
    @Override
    Optional<Charge> findById(Long aLong);
}
