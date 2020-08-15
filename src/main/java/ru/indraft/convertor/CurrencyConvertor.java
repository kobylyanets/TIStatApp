package ru.indraft.convertor;

import ru.indraft.database.model.Currency;

public class CurrencyConvertor {

    public static ru.indraft.database.model.Currency convertToDatabase(
            ru.tinkoff.invest.openapi.models.Currency currency
    ) {
        try {
            return ru.indraft.database.model.Currency.valueOf(currency.name());
        } catch(IllegalArgumentException throwables) {
            throwables.printStackTrace();
            return Currency.UNKNOWN;
        } catch (NullPointerException nullPointerException) {
            return Currency.UNKNOWN;
        }
    }

}
