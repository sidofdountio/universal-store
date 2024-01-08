package com.meche.service;

import com.meche.model.Sale;
import com.meche.repo.SaleRepo;
import com.meche.service.serviceImpl.SaleDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SaleService implements SaleDao {
    private final SaleRepo saleRepo;

    @Override
    public List<Sale> saveSale(List<Sale> sales) {
        log.info("Saved sale {}", sales);
        return saleRepo.saveAllAndFlush(sales);
    }

    @Override
    public Sale updateSale(Sale sale) {
        return saleRepo.save(sale);
    }

    @Override
    public Sale getSale(Long saleId) {
        log.info("Fetching sale by id {} ", saleId);
        return saleRepo.findById(saleId)
                .orElseThrow(() -> new NullPointerException(String.format("Sale with %d not found", saleId)));
    }

    @Override
    public void deleteSale(Long saleIdToDelete) {
        saleRepo.deleteById(saleIdToDelete);
    }

    @Override
    public List<Sale> SALES() {
        log.info("Fetching sales");
        return saleRepo.findAll();
    }

    @Override
    public List<Sale> findByMonthAndYear(Month month, String year, Sort sort) {
        return saleRepo.findByMonthAndYear(month, year, sort.descending());
    }

    @Override
    public List<Sale> findByDayAndMonth(int day, Month month, Sort sort) {
        return saleRepo.findByDayAndMonth(day, month, sort.ascending());
    }

    @Override
    public List<Sale> findByMonth(Month month) {
        return saleRepo.findByMonth(month);
    }

    @Override
    public List<Sale> findByDay(int day) {
        return saleRepo.findByDay(day);
    }
}
