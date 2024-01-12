package com.meche.api.operation;

import com.meche.model.Inventory;
import com.meche.model.Product;
import com.meche.model.Purchase;
import com.meche.model.Sale;
import com.meche.service.InventoryService;
import com.meche.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Transactional
@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryOperation implements StockManager {

    private final ProductService productService;
    private final InventoryService inventoryService;

    /**
     * In this project the new price (That correspond for sale price)
     * Will be provided by the owner or manager.
     * <p>
     * We check up product on our stock.
     * Up product its doeat who have been save laters(Purchase and sale)
     * and the quantity still great thant O.
     * We also check if the provide product exist on our stork or database.
     * In this case the newPrice attribute  will provider.
     */
    @Override
    public Inventory cmupForPurchase(String label, Purchase purchase) {

        List<Inventory> inventoryList = inventoryService.INVENTORY_LIST();
        final Product productById = productService.getProductById(purchase.getProduct().getId());
        int day = LocalDate.now().getDayOfMonth();
        Month month = LocalDate.now().getMonth();
        Year year = Year.now();
        final int quantityPovider = purchase.getQuantity();
        final double price = purchase.getPrice();
        final double amount = quantityPovider * price;

        int newQuantity = 0;
        int newQuantityCalculed = 0;
        final double newPrice = purchase.getSalePrice();
        final double newAmount = purchase.getAmount();
        Inventory inventoryToPucharse = new Inventory();
        Inventory inventoryPresentInStock = new Inventory();
        final String productName = purchase.getProduct().getName();
//        In this case stock is empty.

        int qty = 0;
        double amt = 0;
        Inventory inventoryToAdd = new Inventory();
        if (inventoryList.isEmpty()) {
            Inventory inventoryFirst;
            inventoryFirst = new Inventory(
                    1L, LocalDateTime.now(),
                    label,
                    productName,
                    true,
                    quantityPovider,
                    price,
                    amount,
                    quantityPovider,
                    newPrice,
                    newAmount,
                    day,
                    month,
                    year
            );
            inventoryToPucharse = inventoryFirst;
        }
        boolean productNameExist = false;
        for (Inventory inventoryStock : inventoryList) {

            log.info("all inventory : {}", inventoryStock);
            log.info("product name : {}", productName);

            if (inventoryStock.getProductName().equalsIgnoreCase(productName) && inventoryStock.isUp()) {
                log.info("inventory  filter 1 : {}", inventoryStock);
                productNameExist = true;
                inventoryPresentInStock =  inventoryStock;
                break;
            }
        }
        if (productNameExist){
            log.info("inventory  filter 1 ok : {}", inventoryPresentInStock);
            final double newPriceToAdd = purchase.getSalePrice();
//                orld quantity + new purchase quantity.
            qty = inventoryPresentInStock.getNewQuantity() + quantityPovider; //calcul de la nouvel quatite.
            // calcul du nouveau montant.
            amt = Math.multiplyExact(quantityPovider, (int) newPriceToAdd);

//              Firstly set orld values.
            inventoryToAdd.setLabel(label);
            inventoryToAdd.setProductName(productName);
            inventoryToAdd.setDate(LocalDateTime.now());
            inventoryToAdd.setDay(day);
            inventoryToAdd.setMonth(month);
            inventoryToAdd.setYear(year);

            inventoryToAdd.setOrldQuantity(quantityPovider);
            inventoryToAdd.setOrldPrice(price);
            inventoryToAdd.setOldAmount(amount);

            inventoryToAdd.setNewQuantity(qty);
            inventoryToAdd.setNewPrice(newPrice);
            inventoryToAdd.setNewAmount(amt);
            inventoryToAdd.setUp(true);
            inventoryToPucharse = inventoryToAdd;

            inventoryPresentInStock.setUp(false);

            productById.setPrice(newPrice);
            productService.updateProduct(productById);
             inventoryToPucharse = inventoryToAdd;

        }else {

            Inventory inventoryNew = new Inventory(
                    null, LocalDateTime.now(), label, productName, true,
                    quantityPovider,
                    price,
                    amount,
                    quantityPovider,
                    newPrice,
                    newAmount,
                    day,
                    month,
                    year
            );
            inventoryToPucharse = inventoryNew;
            productById.setPrice(newPrice);
        }
        return inventoryToPucharse;
    }

    @Override
    public List<Inventory> cmupForSale(String label, List<Sale> sales, List<Inventory> inventoryList) {
        int day = LocalDate.now().getDayOfMonth();
        Month month = LocalDate.now().getMonth();
        Year year = Year.now();
        List<Inventory> inventoryToCMUSale = new ArrayList<>();
//      Default values.
        final LocalDate date = now();
//      Last calculed quantity in our stock.
        int newQuantity = 0;
//      Get last pucharse price.
        double newPrice = 0;
        double oldPrice = 0;
        double newAmount = 0;
        double oldAmount = 0;
//      SOME OPERATION (CMUP) Cout Moyen Unitaire Pondere.
        int quantity = 0;
        double amount = 0;
//      Quantity provider by user.
        int quantityProvideByUser = 0;
        Long productId = 0L;
        String productName = "";
        boolean isNotPresent = false;


//      Ensure that with have alredy purchase.
        if (inventoryList.isEmpty()) {
            throw new IllegalStateException("We can't perform this action. First purchase this product");
        }

//      Set Sale properties.
        for (Sale sale : sales) {
//            Object
            Inventory inventoryToAdd = new Inventory();
            quantityProvideByUser = sale.getQuantity();
            productName = sale.getProduct().getName();
            for (Inventory stockInventory : inventoryList) {
//            stock product.

                if (productName.equalsIgnoreCase(stockInventory.getProductName()) && stockInventory.isUp()) {
                    isNotPresent = true;

                    if (stockInventory.getNewQuantity() == 0) {
                        throw new IllegalStateException("Quantity in store it's less thant provider");
                    }
//                  We manage only product that is up.
                    oldPrice = stockInventory.getOrldPrice();
                    newPrice = sale.getPrice();//ancien prix calcule apres achat.

                    oldAmount = quantityProvideByUser * newPrice; // prix calcule
                    newQuantity = stockInventory.getNewQuantity();
                    newAmount = quantityProvideByUser * newPrice;
                    quantity = newQuantity - quantityProvideByUser;
                    inventoryToAdd.setLabel(label);
                    inventoryToAdd.setOrldQuantity(quantityProvideByUser);
                    inventoryToAdd.setOrldPrice(newPrice);// ancien nouveau prix calcule apre achat.
                    inventoryToAdd.setOldAmount(oldAmount);

                    inventoryToAdd.setNewQuantity(quantity);
                    inventoryToAdd.setNewPrice(newPrice);
                    inventoryToAdd.setNewAmount(newAmount);
                    inventoryToAdd.setProductName(productName);
                    inventoryToAdd.setDate(LocalDateTime.now());
                    inventoryToAdd.setYear(year);
                    inventoryToAdd.setMonth(month);
                    inventoryToAdd.setDay(day);

                    inventoryToAdd.setUp(true);
//                  Turn the position of last product to false
                    stockInventory.setUp(false);
//                    Update change of inventory after seller.
//                    Save the change mind save the new quantity that reduce
                    inventoryService.updateInventory(stockInventory);

                }
            }

            if(!isNotPresent){
                throw new IllegalStateException("Please first purchase");
            }
//            Add to list CMUP
            inventoryToCMUSale.add(inventoryToAdd);
        }
        return inventoryToCMUSale;
    }

}
