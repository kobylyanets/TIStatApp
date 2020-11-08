package ru.indraft.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class CommonStatFx {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty value = new SimpleStringProperty();
}
