package it.mattteo.pipitone.sales_taxes_kata;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import static java.math.BigDecimal.ZERO;

public class Item {
    private final ArrayList<String> itemsWithFees;
    public boolean imported;
    private final int quantity;
    private String description;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private BigDecimal taxes;

    public Item(int quantity, String description, BigDecimal price, boolean imported, ArrayList<String> itemsWithFeesDescription) {
        this.quantity = quantity;
        this.imported = imported;
        this.itemsWithFees = itemsWithFeesDescription;
        setDescription(description);
        setPrice(price, quantity);
        calculateTaxes(description, quantity, price);
    }

    public BigDecimal calculatePrice() {
        return totalPrice.add(getTaxes());
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public String printDetails() {
        return quantity + " " + description + ": " + calculatePrice() + "\n";
    }

    private void setDescription(String description) {
        if (description.contains("imported")) {
            this.description = "imported " + description.replace("imported ", "");
        } else {
            this.description = description;
        }
    }

    private void setPrice(BigDecimal price, int quantity) {
        this.price = price;
        this.totalPrice = price.multiply(new BigDecimal(quantity));
    }

    private void calculateTaxes(String description, int quantity, BigDecimal price) {
        this.taxes = ZERO;
        BigDecimal localPrice = price.multiply(new BigDecimal(String.valueOf(quantity)));

        if (itemNeedFees(description)) {
            this.taxes = applyTenPercentOfFees(localPrice);
        }

        if (imported) {
            this.taxes = applyImportedFees(localPrice);
        }

        this.taxes = roundAtNearestFiveCent();
    }

    private BigDecimal roundAtNearestFiveCent() {
        return BigDecimal.valueOf(Math.round(this.taxes.doubleValue() * 100.0 / 5.0) * 5.0 / 100.0);
    }

    private BigDecimal applyImportedFees(BigDecimal localPrice) {
        return this.taxes.add(localPrice.multiply(new BigDecimal("0.05")));
    }

    private BigDecimal applyTenPercentOfFees(BigDecimal localPrice) {
        return this.taxes.add(localPrice.multiply(new BigDecimal(("0.1")), new MathContext(2, RoundingMode.HALF_UP)));
    }

    private boolean itemNeedFees(String description) {
        return itemsWithFees.contains(description.replaceAll("imported ", ""));
    }


    @Override
    public String toString() {
        return "Item{" +
                "itemsWithFees=" + itemsWithFees +
                ", imported=" + imported +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", taxes=" + taxes +
                '}';
    }
}
