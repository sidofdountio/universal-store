package com.meche.model;

import com.meche.model.enume.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
public class InvoicePurchase {
    @Id
    @SequenceGenerator(name = "sequence_id_invoice_purchase", allocationSize = 1, sequenceName = "sequence_id_invoice_purchase")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_invoice_purchase")
    private Long id;
    private double subTotal;
    private double tax;
    private double total;
    private int invoiceNumber;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "invoice_purchase"))
    private Purchase purchase;
}