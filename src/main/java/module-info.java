module ru.indraft {
    opens ru.indraft.database.model to ormlitebuild;
    requires javafx.controls;
    requires openapi.java.sdk.core;
    requires java.logging;
    requires openapi.java.sdk.java8;
    requires com.fasterxml.jackson.core;
    requires java.sql;
    requires ormlitebuild;
    requires lombok;
    exports ru.indraft;
}