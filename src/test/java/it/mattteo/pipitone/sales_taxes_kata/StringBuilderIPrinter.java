package it.mattteo.pipitone.sales_taxes_kata;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class StringBuilderIPrinter implements IPrinter {


    @Override
    public String printDetails(List<String> details) {
        StringBuilder result = new StringBuilder();
        for (String item : details) {
            result.append(item);
        }

        return result.toString();
    }

    @Override
    public String printTaxes(BigDecimal taxes) {

        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        String result = df.format(taxes);

        return "Sales Taxes: " + result + "\n";

    }

    @Override
    public String printTotal(BigDecimal total) {
        return "Total: " + total;
    }
}
