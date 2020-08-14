package ru.indraft;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.indraft.database.manager.DbManager;
import ru.indraft.service.LocaleService;
import ru.indraft.service.TinkoffApiService;


/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    private void handleSynchronize() {
        TinkoffApiService tinkoffApiService = new TinkoffApiService();
        tinkoffApiService.synchronizeStockInstruments();
    }

    @Override
    public void start(Stage stage) {
        DbManager.initDataBase();
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        var vbox = new VBox();
        vbox.setFillWidth(true);
        vbox.getChildren().add(label);
        var btn = new Button(LocaleService.getInstance().get("settings.button.title"));
        btn.setOnAction(actionEvent -> handleSynchronize());
        vbox.getChildren().add(btn);

        var scene = new Scene(new StackPane(vbox), 640, 480);

        stage.setScene(scene);
        stage.show();
    }

}