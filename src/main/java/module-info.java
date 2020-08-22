module ru.indraft {
    opens ru.indraft.database.model to ormlitebuild;
    opens ru.indraft.controller to javafx.fxml;
    requires javafx.controls;
    requires javafx.fxml;
    requires openapi.java.sdk.core;
    requires java.logging;
    requires openapi.java.sdk.java8;
    requires com.fasterxml.jackson.core;
    requires java.sql;
    requires ormlitebuild;
    requires lombok;
    exports ru.indraft;
}