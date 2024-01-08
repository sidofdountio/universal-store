package com.meche.model;


import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 27/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Entity
@Data
public class Stock {
    @Id
    @SequenceGenerator(name = "sequence_id_stock",
            allocationSize = 1,
            sequenceName = "sequence_id_stock")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_stock")
    private Long id;
    private int openingStock;
    private int minimumStock;
    private int securityStock;
    private int alertStock;
    private int averageStock;
    private int finalStock;
    private int availableStock;
    private int outStock;
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private int quantity;
}
