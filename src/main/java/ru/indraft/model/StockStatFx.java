package ru.indraft.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class StockStatFx {

    private final SimpleStringProperty ticker = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty commission = new SimpleStringProperty();
    private final SimpleStringProperty profit = new SimpleStringProperty();

    @Getter
    @Setter
    private Currency currencyParam;
    @Getter
    @Setter
    private BigDecimal profitParam;
    @Getter
    @Setter
    private BigDecimal commissionParam;

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

    public String getCommission() {
        return commission.get();
    }

    public void setCommission(String commission) {
        this.commission.set(commission);
    }

    public SimpleStringProperty commissionProperty() {
        return commission;
    }

}
