package com.meche.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 27/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Employee {
    @Id
    @SequenceGenerator(name = "sequence_id_employee",allocationSize = 1,sequenceName = "sequence_id_employee") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_employee")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phone;
    private String address;
    private LocalDate birthDay;
    private String sexe;
    private double salary;
}

