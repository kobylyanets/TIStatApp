package ru.indraft.model;

import javafx.beans.property.SimpleStringProperty;

public class StockOperationFx {

    private final SimpleStringProperty ticker = new SimpleStringProperty();
    private final SimpleStringProperty profit = new SimpleStringProperty();

    public String getTicker() {
        return ticker.get();
    }

    public void setTicker(String ticker) {
        this.ticker.set(ticker);
    }

    public SimpleStringProperty tickerProperty() {
        return ticker;
    }

    public String getProfit() {
        return profit.get();
    }

    public void setProfit(String profit) {
        this.profit.set(profit);
    }

    public SimpleStringProperty profitProperty() {
        return profit;
    }
}
