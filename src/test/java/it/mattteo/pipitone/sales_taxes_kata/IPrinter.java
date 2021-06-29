package it.mattteo.pipitone.sales_taxes_kata;

import java.math.BigDecimal;
import java.util.List;

public interface IPrinter {

     String printDetails(List<String> details);

     String printTaxes(BigDecimal taxes);

     String printTotal(BigDecimal total);
}
