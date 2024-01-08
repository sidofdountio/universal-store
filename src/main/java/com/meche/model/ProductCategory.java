package com.meche.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class ProductCategory {
    @Id
    @SequenceGenerator(name = "sequence_id_product_categorie",
            allocationSize = 1,
            sequenceName = "sequence_id_product_categorie")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_product_categorie")
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "productCategory")
    private List<Product> product = new ArrayList<>();

}

