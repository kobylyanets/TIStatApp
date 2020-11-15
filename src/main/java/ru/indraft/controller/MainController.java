package ru.indraft.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.service.OpenApiService;
import ru.indraft.utils.FxmlUtils;

public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private static final String COMMON_STAT_TAB_PATH = "/views/CommonStatTab.fxml";
    private static final String STOCK_STAT_TAB_PATH = "/views/StocksStatTab.fxml";

    @FXML
    private BorderPane borderPaneMain;

    @FXML
    private void handleSynchronize() {
        OpenApiService tinkoffApiService = new OpenApiService();
        tinkoffApiService.synchronizeStockInstruments();
        tinkoffApiService.synchronizeOperations();
    }

    public void initialize() {
        LOGGER.trace("Initialize Main Controller");
        VBox pageContent = FxmlUtils.loadView(STOCK_STAT_TAB_PATH);
        borderPaneMain.setCenter(pageContent);
    }

}
