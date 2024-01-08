package com.meche.service.serviceImpl;

import com.meche.model.Sale;
import org.springframework.data.domain.Sort;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface SaleDao {
    List<Sale> saveSale(List<Sale> sale);

    Sale updateSale(Sale sale);

    Sale getSale(Long saleId);

    void deleteSale(Long saleIdToDelete);

    List<Sale> SALES();

    List<Sale> findByMonthAndYear(Month month, String year, Sort sort);

    List<Sale> findByDayAndMonth(int day, Month month, Sort sort);

    List<Sale> findByMonth(Month month);
    List<Sale> findByDay(int day);
}
