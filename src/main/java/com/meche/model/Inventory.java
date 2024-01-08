package com.meche.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @parame label it's describe the type of mouvement: Purchase or Sale.
 * @parame date When the mouvement it's happend.
 * @parame quantity last quanity before any mouvement.
 * @parame price last price before any mouvement.
 * @parame amount last amount before any mouvement
 * @parame newQuantity  newQuantity after any mouvement
 * @parame newPrice  newPrice  after any mouvement.
 * @parame newAmount  newAmount  after any mouvement.
 *
 * <h4>Operation Calcul.</h4>
 * <ul>
 *     <li>Purchase operation</li>
 *     oldQuantity = value.<br>
 *     oldPrice = value.<br>
 *     oldAmount = oldQuantity  *  oldPrice.<br>
 *     newQuantity = oldQuantity(currentListIndex -1) + newQuantity.</br>
 *     newAmount = oldAmount(currentListIndex -1) + newAmount.</br>
 *     newPrice = newAmount / newQuantity.</br>
 *     <li>Sale operation</li>
 *     <ol>
 *         <li>oldQuantity = value.</li>
 *         <li>oldPrice = newPrice(currentListIndex -1).</li>
 *         <li>oldAmount = oldQuantity * oldPrice</li>
 *         <li>newQuantity = newQuantity(currentListIndex - 1) - valueOfNewQuantity</li>
 *         <li>newAmount = newAmount(currentListIndex - 1) - oldAmount(currentListIndex) </li>
 *     </ol>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Inventory {
    @Id
    @SequenceGenerator(name = "sequence_id_inventory", allocationSize = 1, sequenceName = "sequence_id_inventory")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_inventory")
    private Long id;
    private LocalDateTime date;
    private String label;
    private String productName;
    private boolean up;
    private int orldQuantity;
    private double orldPrice;
    private double oldAmount;
    private int newQuantity;
    private double newPrice;
    private double newAmount;
    private int day;
    private Month month;
    private Year year;
}