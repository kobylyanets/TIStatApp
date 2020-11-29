package ru.indraft.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.service.OpenApiService;
import ru.indraft.utils.FxmlUtils;
import ru.indraft.utils.JavaFXUtils;

import java.io.IOException;

public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private static final String COMMON_STAT_PAGE_PATH = "/views/CommonStatTab.fxml";
    private static final String STOCK_STAT_PAGE_PATH = "/views/StocksStatTab.fxml";

    IPageController childController;

    @FXML
    private BorderPane borderPaneMain;
    @FXML
    public ToggleGroup navigationToggleGroup;

    @FXML
    private void handleSynchronize() {
        OpenApiService tinkoffApiService = new OpenApiService();
        tinkoffApiService.synchronizeMarketStocks();
        tinkoffApiService.synchronizeOperations();
        childController.refresh();
    }

    private String getStartPage() {
        return COMMON_STAT_PAGE_PATH;
    }

    public void initialize() {
        LOGGER.trace("Initialize Main Controller");
        JavaFXUtils.get().addAlwaysOneSelectedSupport(navigationToggleGroup);
        goToPage(getStartPage());
    }

    private void goToPage(String pathToView) {
        try {
            var loader = FxmlUtils.getLoader(pathToView);
            if (loader != null) {
                VBox pageContent = loader.load();
                borderPaneMain.setCenter(pageContent);
                childController = loader.getController();
            }
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @FXML
    public void handleGoToStockStatPage() {
        goToPage(STOCK_STAT_PAGE_PATH);
    }

    @FXML
    public void handleGoToCommonStatPage() {
        goToPage(COMMON_STAT_PAGE_PATH);
    }
}
