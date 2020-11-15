package ru.indraft.service;

import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.model.StockStatFx;

import java.util.ArrayList;
import java.util.List;

public class StocksStatService {

    private final InstrumentDao instrumentDao = new InstrumentDao();
    private final OperationDao operationDao = new OperationDao();

    public List<StockStatFx> getStocksStat() {
        return new ArrayList<>();
    }

}
