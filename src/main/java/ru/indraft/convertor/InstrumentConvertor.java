package ru.indraft.convertor;

import java.util.ArrayList;
import java.util.List;

public class InstrumentConvertor {

    public static List<ru.indraft.database.model.Instrument> convertToDatabase(
            List<ru.tinkoff.invest.openapi.models.market.Instrument> instruments
    ) {
        List<ru.indraft.database.model.Instrument> instrumentsDb = new ArrayList<>();
        for (var instrument : instruments) {
            instrumentsDb.add(convertToDatabase(instrument));
        }
        return instrumentsDb;
    }

    public static ru.indraft.database.model.Instrument convertToDatabase(
            ru.tinkoff.invest.openapi.models.market.Instrument instrument
    ) {
        var instrumentDb = new ru.indraft.database.model.Instrument();
        instrumentDb.setFigi(instrument.figi);
        instrumentDb.setTicker(instrument.ticker);
        instrumentDb.setIsin(instrument.isin);
        instrumentDb.setName(instrument.name);
        instrumentDb.setType(InstrumentTypeConvertor.toDatabase(instrument.type));
        instrumentDb.setCurrency(CurrencyConvertor.toDatabase(instrument.currency));
        return instrumentDb;
    }

}
