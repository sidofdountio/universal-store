package com.meche.repo;

import com.meche.model.InvoiceSale;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InvoiceSaleRepo extends JpaRepository<InvoiceSale, Long> {
    @Override
    @Query("SELECT DISTINCT i FROM InvoiceSale i  ORDER BY i.id desc ")
    List<InvoiceSale> findAll();
    @Query("SELECT i FROM InvoiceSale i WHERE i.invoiceNumber = :invoiceNumber")
    List<InvoiceSale> findByInvoiceNumber(@Param("invoiceNumber") String invoiceNumber);

    @Query("SELECT i FROM InvoiceSale i WHERE i.month = :month AND i.year = :year")
    List<InvoiceSale> findByMonthAndYear(@Param("month") Month month, @Param("year") String year, Sort sort);

    @Query("SELECT i FROM InvoiceSale i WHERE i.day = :day AND i.month = :month ")
    List<InvoiceSale> findByDayAndMonth(@Param("day") int day, @Param("month") Month month, Sort sort);

    List<InvoiceSale> findAllByCustomerId(Long customerId);

}
