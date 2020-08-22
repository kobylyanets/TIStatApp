package ru.indraft;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.indraft.manager.DbManager;
import ru.indraft.manager.OpenApiManager;
import ru.indraft.service.LocaleService;
import ru.indraft.service.OpenApiService;
import ru.indraft.utils.FxmlUtils;


/**
 * JavaFX App
 */
public class App extends Application {

    private static final String MAIN_WINDOW_PATH = "/views/Main.fxml";

    public static void main(String[] args) {
        launch();
    }

    private static void initCleanupProcedure() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("close OpenApi");
            OpenApiManager.closeOpenApi();
        }));
    }

    private void handleSynchronize() {
        OpenApiService tinkoffApiService = new OpenApiService();
        tinkoffApiService.synchronizeStockInstruments();
        tinkoffApiService.synchronizeOperations();
    }

    @Override
    public void start(Stage stage) {
        initCleanupProcedure();
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
        // var scene = new Scene(new StackPane(vbox), 640, 480);

        BorderPane borderPane = FxmlUtils.loadView(MAIN_WINDOW_PATH);
        Scene scene = null;
        if (borderPane != null) {
            scene = new Scene(borderPane);
        }
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("close app");
            Platform.exit();
            System.exit(0);
        });
        stage.show();

    }

}