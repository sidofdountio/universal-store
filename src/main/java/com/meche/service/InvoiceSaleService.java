package com.meche.service;

import com.meche.model.InvoiceSale;
import com.meche.repo.InvoiceSaleRepo;
import com.meche.service.serviceImpl.InvoiceSaleDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceSaleService implements InvoiceSaleDao {
    private final InvoiceSaleRepo invoiceSaleRepo;


    @Override
    public void addInvoiceSale(List<InvoiceSale> invoiceSale) {
        log.info("saved new invoice sale.");
        invoiceSaleRepo.saveAll(invoiceSale);
    }

    /**
     * NEW approaches to retrieving data
     */
    @Override
    public List<InvoiceSale> findByInvoiceNumber() {
        log.info("Fetching Invoice Sale By Invoice number .");
//        return invoiceSaleRepo.findDistinctInvoiceNumber();
        return invoiceSaleRepo.findAll();
    }

    @Override
    public List<InvoiceSale> findByInvoiceNumber(String invoicenumber) {
        log.info("Fetching Invoice Sale By Invoice number {} .", invoicenumber);
        return invoiceSaleRepo.findByInvoiceNumber(invoicenumber);
    }

    @Override
    public List<InvoiceSale> findByMonthAndYear(Month month, String year, Sort sort) {
        log.info("Fetching Invoice Sale By month {} and year {} .", month, year);
        return invoiceSaleRepo.findByMonthAndYear(month, year, sort.descending());
    }

    @Override
    public List<InvoiceSale> findByDayAndMonth(int day, Month month, Sort sort) {
        log.info("Fetching Invoice Sale By day {} and month {} .", day, month);
        return invoiceSaleRepo.findByDayAndMonth(day, month, sort.ascending());
    }
}
