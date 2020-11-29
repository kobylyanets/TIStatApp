package ru.indraft.model;

import javafx.beans.property.SimpleStringProperty;

public class CommonStatFx {

    private final SimpleStringProperty parameter = new SimpleStringProperty();
    private final SimpleStringProperty valueInRub = new SimpleStringProperty();
    private final SimpleStringProperty valueInUsd = new SimpleStringProperty();
    private final SimpleStringProperty valueInEur = new SimpleStringProperty();

    public String getParameter() {
        return parameter.get();
    }

    public SimpleStringProperty parameterProperty() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter.set(parameter);
    }

    public String getValueInRub() {
        return valueInRub.get();
    }

    public void setValueInRub(String valueInRub) {
        this.valueInRub.set(valueInRub);
    }

    public SimpleStringProperty valueInRubProperty() {
        return valueInRub;
    }

    public String getValueInUsd() {
        return valueInUsd.get();
    }

    public void setValueInUsd(String valueInUsd) {
        this.valueInUsd.set(valueInUsd);
    }

    public SimpleStringProperty valueInUsdProperty() {
        return valueInUsd;
    }

    public String getValueInEur() {
        return valueInEur.get();
    }

    public void setValueInEur(String valueInEur) {
        this.valueInEur.set(valueInEur);
    }

    public SimpleStringProperty valueInEurProperty() {
        return valueInEur;
    }

}
