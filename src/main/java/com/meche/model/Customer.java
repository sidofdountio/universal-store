package com.meche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "sequence_id_customer", allocationSize = 1, sequenceName = "sequence_id_customer")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_customer")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = true)
    private String email;
    @Column(nullable = false)
    private String phone;
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Sale> sales = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<InvoiceSale> invoiceSales = new ArrayList<>();
}
