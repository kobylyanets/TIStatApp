package ru.indraft.model;

import javafx.beans.property.SimpleStringProperty;

public class CommonStatFx {
    private final SimpleStringProperty parameter = new SimpleStringProperty();
    private final SimpleStringProperty value = new SimpleStringProperty();

    public String getParameter() {
        return parameter.get();
    }

    public void setParameter(String parameter) {
        this.parameter.set(parameter);
    }

    public SimpleStringProperty parameterProperty() {
        return parameter;
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }
}
