package com.meche.service.serviceImpl;

import com.meche.model.InvoiceSale;
import org.springframework.data.domain.Sort;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InvoiceSaleDao {

    void addInvoiceSale(List<InvoiceSale> invoiceSales);
//    UPDATE

    List<InvoiceSale> findByInvoiceNumber();

    List<InvoiceSale> findByInvoiceNumber(String invoiceNumber);

    List<InvoiceSale> findByMonthAndYear(Month month, String year, Sort sort);

    List<InvoiceSale> findByDayAndMonth(int day, Month month, Sort sort);

}
