package ru.indraft.convertor;

public class InstrumentTypeConvertor {

    public static ru.indraft.database.model.InstrumentType convertToDatabase(
            ru.tinkoff.invest.openapi.models.market.InstrumentType instrumentType
    ) {
        try {
            return ru.indraft.database.model.InstrumentType.valueOf(instrumentType.name());
        } catch (IllegalArgumentException throwables) {
            throwables.printStackTrace();
            return ru.indraft.database.model.InstrumentType.Unknown;
        }
    }

}
