package ru.indraft.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.service.OpenApiService;
import ru.indraft.utils.FxmlUtils;

import java.io.IOException;

public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private static final String COMMON_STAT_TAB_PATH = "/views/CommonStatTab.fxml";
    private static final String STOCK_STAT_TAB_PATH = "/views/StocksStatTab.fxml";

    IPageController childController;

    @FXML
    private BorderPane borderPaneMain;

    @FXML
    private void handleSynchronize() {
        OpenApiService tinkoffApiService = new OpenApiService();
        tinkoffApiService.synchronizeMarketStocks();
        tinkoffApiService.synchronizeOperations();
        childController.refresh();
    }

    public void initialize() throws IOException {
        LOGGER.trace("Initialize Main Controller");
        var loader = FxmlUtils.getLoader(STOCK_STAT_TAB_PATH);
        if (loader != null) {
            VBox pageContent = loader.load();
            borderPaneMain.setCenter(pageContent);
            childController = loader.getController();
        }
    }

}
