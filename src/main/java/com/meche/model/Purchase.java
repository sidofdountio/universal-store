package com.meche.model;


import com.meche.model.enume.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Purchase {
    @Id
    @SequenceGenerator(name = "sequence_id_purchase", allocationSize = 1, sequenceName = "sequence_id_purchase")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_purchase")
    private Long id;
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double salePrice;
    private double amount;
    private LocalDate purchaseAt;
    @Enumerated(STRING)
    private Status status;
    private int day;
    private Month month;
    private Year year;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "sale_purchase"))
    private Supplier supplier;
    //    @ManyToOne(cascade = DETACH)
    @ManyToOne
    @JoinColumn(name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "product_purchase"))
    private Product product;
    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "transaction_sale"))
    private Transaction transaction;
}

