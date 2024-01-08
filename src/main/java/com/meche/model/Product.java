package com.meche.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
@Builder
@Entity
public class Product {
    @Id
    @SequenceGenerator(name = "sequence_id_product", allocationSize = 1, sequenceName = "sequence_id_product")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_product")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = true)
    private String color;
    @Column(nullable = true)
    private double length;
    @Column(nullable = true)
    private double salePrice;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(unique = true, nullable = false)
    private String code;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    private Sale sale;
    @OneToOne(mappedBy = "product")
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "product_category"))
    private ProductCategory productCategory;
    private String volume;
}

