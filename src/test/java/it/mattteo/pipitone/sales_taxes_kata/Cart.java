package it.mattteo.pipitone.sales_taxes_kata;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

public class Cart {
    private final List<Item> items = new ArrayList<Item>();
    private BigDecimal totalPrice = ZERO;
    private BigDecimal taxes = ZERO;

    public Cart(String[] items) {
        for (String itemString : items) {
            Item item = ItemFactory.createItem(itemString);
            totalPrice = totalPrice.add(item.calculatePrice());
            taxes = taxes.add(item.getTaxes());
            this.items.add(item);
        }
    }

    public String printReceipt(IPrinter iPrinter) {
        return iPrinter.printDetails(items.stream().map(Item::printDetails).collect(Collectors.toList())) + iPrinter.printTaxes(taxes) + iPrinter.printTotal(totalPrice);
    }
}
