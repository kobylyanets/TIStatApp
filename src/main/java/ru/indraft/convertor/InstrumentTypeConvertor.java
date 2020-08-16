package ru.indraft.convertor;

import static ru.indraft.utils.ConvertUtils.convertEnum;

public class InstrumentTypeConvertor {

    public static ru.indraft.database.model.InstrumentType toDatabase(
            ru.tinkoff.invest.openapi.models.market.InstrumentType instrumentType
    ) {
        return convertEnum(
                ru.indraft.database.model.InstrumentType.class,
                instrumentType
        );
    }

    public static ru.indraft.database.model.InstrumentType toDatabase(
            ru.tinkoff.invest.openapi.models.portfolio.InstrumentType instrumentType
    ) {
        return convertEnum(
                ru.indraft.database.model.InstrumentType.class,
                instrumentType
        );
    }
}