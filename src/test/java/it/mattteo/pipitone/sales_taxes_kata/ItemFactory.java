package it.mattteo.pipitone.sales_taxes_kata;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.math.BigDecimal.ZERO;

public class ItemFactory {

    public static ArrayList<String> items = new ArrayList<>() {{
        add("music CD");
        add("bottle of perfume");
    }};

    private static boolean parameterIsASpace(String parameterString, int index) {
        return parameterString.charAt(index) == ' ';
    }

    public static Item createItem(String parameterString) {

        int firstSplit = 0;
        int quantity = 0;
        boolean imported = false;
        String description = "";
        BigDecimal price = ZERO;

        for (int j = 0; j < parameterString.length(); j++) {

            if (parameterIsASpace(parameterString, j) && firstSplit == 0) {
                quantity = Integer.parseInt(parameterString.substring(0, j));
                firstSplit = j;
            }

            if (
                    parameterString.charAt(j) == 'i' &&
                            parameterIsASpace(parameterString, j - 1) &&
                            parameterString.charAt(j + 1) == 'm' &&
                            parameterString.charAt(j + 2) == 'p' &&
                            parameterIsASpace(parameterString, j + 8)
            ) {

                imported = true;
            }

            if (parameterString.charAt(j) == 'a' && parameterIsASpace(parameterString, j - 1) && parameterString.charAt(j + 1) == 't' && parameterIsASpace(parameterString, j + 2)) {
                description = parameterString.substring(firstSplit + 1, j - 1);
                price = new BigDecimal(parameterString.substring(j + 3));
            }

        }

        return new Item(quantity, description, price, imported, items);
    }
}
