package ru.indraft.service;

import ru.indraft.database.model.Instrument;
import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;
import ru.indraft.model.StockOperationFx;
import ru.indraft.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonStatService {

    public static Double getMarginCommissionSum(List<Operation> operations) {
        return operations.stream()
                .filter(operation -> operation.getOperationType() == OperationType.MarginCommission)
                .map(Operation::getPayment)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }

    public static Set<String> getAllTickers(List<Operation> operations) {
        return operations.stream()
                .map(Operation::getInstrument)
                .filter(Objects::nonNull)
                .map(Instrument::getTicker)
                .collect(Collectors.toSet());
    }

    public static Instrument findInstrumentByTicker(List<Operation> operations, String ticker) {
        return operations.stream()
                .map(Operation::getInstrument)
                .filter(Objects::nonNull)
                .filter(instrument -> instrument.getTicker().equals(ticker)).findFirst().orElse(null);
    }

    public static List<StockOperationFx> getStockStatOperations(List<Operation> operations) {
        var tickers = getAllTickers(operations);
        var list = new ArrayList<StockOperationFx>();
        for (String ticker : tickers) {
            var profit = getTickerProfit(ticker, operations);
            if (profit != null) {
                var stockOperation = new StockOperationFx();
                stockOperation.setTicker(ticker);
                var instrument = findInstrumentByTicker(operations, ticker);
                stockOperation.setName(instrument.getName());
                stockOperation.setProfit(MoneyUtils.format(profit, instrument.getCurrency()));
                list.add(stockOperation);
            }
        }
        return list;
    }

    public static BigDecimal getTickerProfit(String ticker, List<Operation> operations) {
        var tickerOperations = operations.stream()
                .filter(operation -> operation.getInstrument() != null)
                .filter(operation -> operation.getInstrument().getTicker().equals(ticker))
                .collect(Collectors.toList());
        var buyOperations = tickerOperations.stream()
                .filter(operation -> operation.getOperationType() == OperationType.Buy || operation.getOperationType() == OperationType.BuyCard)
                .collect(Collectors.toList());
        var sellOperations = tickerOperations.stream()
                .filter(operation -> operation.getOperationType() == OperationType.Sell)
                .collect(Collectors.toList());
        var quantityBuy = buyOperations.stream().map(Operation::getQuantity).reduce(0, Integer::sum);
        var quantitySell = sellOperations.stream().map(Operation::getQuantity).reduce(0, Integer::sum);
        if (quantityBuy - quantitySell == 0) {
            var buySum = buyOperations.stream()
                    .map(Operation::getPayment)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).abs();
            var sellSum = sellOperations.stream()
                    .map(Operation::getPayment)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).abs();
            return sellSum.subtract(buySum);
        }
        return null;
    }


}
