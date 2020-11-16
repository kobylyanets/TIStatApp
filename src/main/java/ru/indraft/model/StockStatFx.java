package ru.indraft.model;

import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class StockStatFx {

    private final SimpleStringProperty ticker = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty profit = new SimpleStringProperty();

    private Currency currencyParam;
    private BigDecimal profitParam;

    public String getTicker() {
        return ticker.get();
    }

    public SimpleStringProperty tickerProperty() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker.set(ticker);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getProfit() {
        return profit.get();
    }

    public SimpleStringProperty profitProperty() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit.set(profit);
    }

    public Currency getCurrencyParam() {
        return currencyParam;
    }

    public void setCurrencyParam(Currency currencyParam) {
        this.currencyParam = currencyParam;
    }

    public BigDecimal getProfitParam() {
        return profitParam;
    }

    public void setProfitParam(BigDecimal profitParam) {
        this.profitParam = profitParam;
    }
}
