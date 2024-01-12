package com.meche.repo;

import com.meche.model.Sale;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */

public interface SaleRepo extends JpaRepository<Sale, Long> {
    @Override
    @Query("SELECT i FROM Sale i  ORDER BY i.id desc")
    List<Sale> findAll();


    @Query("SELECT i FROM Sale i WHERE i.month = :month AND i.year = :year order by i.id desc")
    List<Sale> findByMonthAndYear(@Param("month") Month month, @Param("year") String year);

    @Query("SELECT i FROM Sale i WHERE i.day = :day AND i.month = :month ")
    List<Sale> findByDayAndMonth(@Param("day") int day, @Param("month") Month month, Sort sort);

    List<Sale> findByDayAndMonth(int day, Month month);

    @Query("SELECT i FROM Sale i WHERE i.month = ?1 ")
    List<Sale> findByMonth(Month month);

    @Query("SELECT i FROM Sale i WHERE i.day = ?1 ")
    List<Sale> findByDay(int day);


}
