package ru.indraft.convertor;

import static ru.indraft.utils.ConvertUtils.convertEnum;

public class CurrencyConvertor {

    public static ru.indraft.database.model.Currency toDatabase(
            ru.tinkoff.invest.openapi.models.Currency currency
    ) {
        return convertEnum(
                ru.indraft.database.model.Currency.class,
                currency
        );
    }

}
