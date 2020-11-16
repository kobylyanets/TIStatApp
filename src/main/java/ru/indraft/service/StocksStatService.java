package ru.indraft.service;

import ru.indraft.convertor.CurrencyConvertor;
import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.database.model.Instrument;
import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;
import ru.indraft.model.Currency;
import ru.indraft.model.StockStatFx;
import ru.indraft.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StocksStatService {

    private final InstrumentDao instrumentDao = new InstrumentDao();
    private final OperationDao operationDao = new OperationDao();

    public List<StockStatFx> getStocksStat() {
        var operations = operationDao.queryForAll();
        var stocks = instrumentDao.queryForAll();
        var tickets = findAllTicketsOfOperations(operations);
        var stocksStat = new ArrayList<StockStatFx>();
        tickets.forEach(ticket -> {
            var stockStat = getStockStat(ticket, operations, stocks);
            if (stockStat != null) {
                stocksStat.add(stockStat);
            }
        });
        return stocksStat;
    }

    public BigDecimal getTotalSumByCurrency(List<StockStatFx> stocksStat, Currency currency) {
        return stocksStat.stream()
                .filter(stockStat -> stockStat.getCurrencyParam() == currency)
                .map(StockStatFx::getProfitParam)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .abs();
    }

    private Set<String> findAllTicketsOfOperations(List<Operation> operations) {
        return operations.stream()
                .map(Operation::getInstrument)
                .filter(Objects::nonNull)
                .map(Instrument::getTicker)
                .collect(Collectors.toSet());
    }

    private List<Operation> findOperationsByTicker(String ticker, List<Operation> operations) {
        return operations.stream()
                .filter(operation -> operation.getInstrument() != null)
                .filter(operation -> operation.getInstrument().getTicker().equals(ticker))
                .collect(Collectors.toList());
    }

    private List<Operation> filterOperationsByTypes(List<Operation> operations, OperationType... operationTypes) {
        return operations.stream()
                .filter(
                        operation -> Arrays
                                .stream(operationTypes)
                                .anyMatch(operationType -> operation.getOperationType() == operationType)
                )
                .collect(Collectors.toList());
    }

    private int getTotalQuantityOfOperations(List<Operation> operations) {
        return operations.stream().map(Operation::getQuantity).reduce(0, Integer::sum);
    }

    private BigDecimal getTotalSumOfOperations(List<Operation> operations) {
        return operations.stream()
                .map(Operation::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .abs();
    }

    private boolean isClosedPosition(List<Operation> buyOperations, List<Operation> sellOperations) {
        var buyQuantity = getTotalQuantityOfOperations(buyOperations);
        var sellQuantity = getTotalQuantityOfOperations(sellOperations);
        return buyQuantity - sellQuantity == 0;
    }

    private Currency findStockCurrency(List<Operation> stockOperations) {
        return CurrencyConvertor.toFx(stockOperations.stream()
                .map(Operation::getCurrency)
                .filter(Objects::nonNull)
                .findFirst().orElse(null));
    }

    private Instrument getStockByTicker(String ticker, List<Instrument> stocks) {
        return stocks.stream()
                .filter(stock -> stock.getTicker().equals(ticker))
                .findFirst()
                .orElse(null);
    }

    private BigDecimal getStockProfit(List<Operation> buyOperations, List<Operation> sellOperations) {
        var buySum = getTotalSumOfOperations(buyOperations);
        var sellSum = getTotalSumOfOperations(sellOperations);
        return sellSum.subtract(buySum);
    }

    private StockStatFx getStockStat(String ticker, List<Operation> operations, List<Instrument> stocks) {
        var stockOperations = findOperationsByTicker(ticker, operations);
        var buyOperations = filterOperationsByTypes(stockOperations, OperationType.Buy, OperationType.BuyCard);
        var sellOperations = filterOperationsByTypes(stockOperations, OperationType.Sell);
        if (isClosedPosition(buyOperations, sellOperations)) {
            var stockStat = new StockStatFx();
            stockStat.setTicker(ticker);
            var currency = findStockCurrency(stockOperations);
            stockStat.setCurrencyParam(currency);
            var stock = getStockByTicker(ticker, stocks);
            stockStat.setName(stock.getName());
            var stockProfit = getStockProfit(buyOperations, sellOperations);
            stockStat.setProfit(MoneyUtils.format(stockProfit, currency));
            stockStat.setProfitParam(stockProfit);
            return stockStat;
        }
        return null;
    }

}
