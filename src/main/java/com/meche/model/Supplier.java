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
public class Supplier {
    @Id
    @SequenceGenerator(name = "sequence_id_supplier", allocationSize = 1, sequenceName = "sequence_id_supplier")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_supplier")
    private Long id;
    private String name;
    @Column(unique = true, nullable = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column(nullable = true)
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private List<Purchase> purchases = new ArrayList<>();
}

