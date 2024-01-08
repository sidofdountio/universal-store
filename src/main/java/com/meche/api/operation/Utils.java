package com.meche.api.operation;

import java.util.UUID;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public class Utils {
    public static String generateInvoiceNumber() {
        UUID uuid = UUID.randomUUID();
        String invoiceNumber = uuid.toString().replace("-", "").substring(0, 10);
        return "INV"+invoiceNumber.toUpperCase();
    }

    public static  String generateTransactionId(){
        String transactionId = UUID.randomUUID().toString().toUpperCase();
        return "TRA-"+transactionId;
    }
}
