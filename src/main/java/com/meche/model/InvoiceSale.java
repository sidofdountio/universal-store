package com.meche.model;


import com.meche.model.enume.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 27/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InvoiceSale {
    @Id
    @SequenceGenerator(name = "sequence_id_invoice", allocationSize = 1, sequenceName = "sequence_id_invoice")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_invoice")
    private Long id;
    @Enumerated(STRING)
    private Status status;
    private double subTotal;
    private double tax;
    private double total;
    @Column(nullable = false)
    private String invoiceNumber;
    private LocalDateTime createAt;
    private int day;
    private Month month;
    private String year;
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "invoice_sale"))
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "customer_id",
            nullable = true,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoice_customer"))
    private Customer customer;
}

