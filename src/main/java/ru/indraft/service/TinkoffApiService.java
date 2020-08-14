package ru.indraft.service;


import ru.indraft.convertor.InstrumentConvertor;
import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.model.Instrument;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.models.market.InstrumentsList;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApiFactory;

import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class TinkoffApiService {

    private static final String TOKEN_KEY = "tinkoff.invest.openapi.token";
    private static final String SANDBOX_TOKEN_KEY = "tinkoff.invest.openapi.sandbox.token";

    private final Logger logger = Logger.getLogger(TinkoffApiService.class.getName());
    private OpenApi api;

    public OpenApi getOpenApi() {
        if (api == null) {
            api = createOpenApi();
        }
        return api;
    }

    private OpenApi createOpenApi() {
        var factory = new OkHttpOpenApiFactory(getToken(), logger);
        return factory.createOpenApiClient(Executors.newSingleThreadExecutor());
    }

    public void closeOpenApi() {
        if (api != null) {
            try {
                api.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ResourceBundle getSecretResourceBundle() {
        return ResourceBundle.getBundle("bundles.secret");
    }

    private String getToken() {
        return getSecretResourceBundle().getString(TOKEN_KEY);
    }

    private String getSandboxToken() {
        return getSecretResourceBundle().getString(SANDBOX_TOKEN_KEY);
    }

    public void synchronizeStockInstruments() {
        InstrumentDao instrumentDao = new InstrumentDao();
        InstrumentsList instrumentList = getOpenApi().getMarketContext().getMarketStocks().join();
        instrumentDao.createOrUpdate(InstrumentConvertor.convertToDatabase(instrumentList.instruments), Instrument.class);
        closeOpenApi();
    }

}
