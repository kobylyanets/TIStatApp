package ru.indraft.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyUtils {

    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("Ru"));

    public static String format(BigDecimal value, ru.indraft.database.model.Currency currency) {
        formatter.setCurrency(Currency.getInstance(currency.name()));
        return formatter.format(value);
    }

}
