package it.mattteo.pipitone.sales_taxes_kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalesTaxesMainTest {

    @Test
    void simplyAsMuchAsPossible() {

        String input = "1 book at 12.49\n";
        String output = "1 book: 12.49\nSales Taxes: 0.00\nTotal: 12.49";

        String result = printReceipt(input);

        Assertions.assertEquals(output, result);
    }

    @Test
    void printTwoElements() {

        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99";
        String output = "1 book: 12.49\n1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 28.98";

        String result = printReceipt(input);

        Assertions.assertEquals(output, result);
    }

    @Test
    void firstCase() {

        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        String output = "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83";

        String result = printReceipt(input);

        Assertions.assertEquals(output, result);
    }

    @Test
    void testSecondCase() {

        String input = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";

        String output = "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.70\n" +
                "Sales Taxes: 7.70\n" +
                "Total: 65.20";

        String result = printReceipt(input);

        Assertions.assertEquals(output, result);

    }

    @Test
    void testThirdCase() {
        String input = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "1 box of imported chocolates at 11.25";

        String output = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.80\n" +
                "Sales Taxes: 6.65\n" +
                "Total: 74.63";

        String result = printReceipt(input);

        Assertions.assertEquals(output, result);
    }

    private String printReceipt(String input) {
        Cart cart = new Cart(input.split("\n"));
        return cart.printReceipt(new StringBuilderIPrinter());
    }

}