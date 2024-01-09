package com.meche.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Benefice {
    private double sale;
    private double purchase;
    private double charge;
    private double potentialWinner;
}
