package ru.indraft.service;


import ru.indraft.convertor.InstrumentConvertor;
import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.model.Instrument;
import ru.indraft.manager.OpenApiManager;
import ru.tinkoff.invest.openapi.models.market.InstrumentsList;

public class OpenApiService {

    public void synchronizeStockInstruments() {
        InstrumentDao instrumentDao = new InstrumentDao();
        InstrumentsList instrumentList = OpenApiManager.getOpenApi().getMarketContext().getMarketStocks().join();
        instrumentDao.createOrUpdate(InstrumentConvertor.convertToDatabase(instrumentList.instruments), Instrument.class);
    }
}