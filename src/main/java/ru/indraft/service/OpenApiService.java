package ru.indraft.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.convertor.InstrumentConvertor;
import ru.indraft.convertor.OperationConvertor;
import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.database.model.Instrument;
import ru.indraft.database.model.Operation;
import ru.indraft.manager.OpenApiManager;
import ru.indraft.utils.DateUtils;
import ru.tinkoff.invest.openapi.models.market.InstrumentsList;
import ru.tinkoff.invest.openapi.models.operations.OperationStatus;

import java.util.stream.Collectors;

public class OpenApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiService.class);

    public void synchronizeStockInstruments() {
        InstrumentDao instrumentDao = new InstrumentDao();
        InstrumentsList instrumentList = OpenApiManager.getOpenApi().getMarketContext().getMarketStocks().join();
        instrumentDao.createOrUpdate(InstrumentConvertor.convertToDatabase(instrumentList.instruments), Instrument.class);
    }

    public void synchronizeOperations() {
        var startDate = DateUtils.getOffsetDateOfFoundationTI();
        var endDate = DateUtils.getOffsetEndTimeOfCurrentDay();
        var operationsList =
                OpenApiManager
                        .getOpenApi()
                        .getOperationsContext()
                        .getOperations(
                                startDate,
                                endDate,
                                null,
                                null
                        ).join();
        var filteredOperations =
                operationsList.operations.stream()
                        .filter(operation -> operation.status == OperationStatus.Done)
                        .collect(Collectors.toList());
        OperationDao operationDao = new OperationDao();
        operationDao.createOrUpdate(
                OperationConvertor.convertToDatabase(filteredOperations),
                Operation.class
        );
        var list = operationDao.queryForAll(Operation.class);
        LOGGER.info("FILTERED OPERATIONS SIZE {}:", filteredOperations.size());
    }
}