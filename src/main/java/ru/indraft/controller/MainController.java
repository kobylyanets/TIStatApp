package ru.indraft.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.service.LocaleService;
import ru.indraft.service.OpenApiService;
import ru.indraft.utils.FxmlUtils;

public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private static final String COMMON_STAT_TAB_PATH = "/views/CommonStatTab.fxml";

    @FXML
    private TabPane tabPane;

    @FXML
    private void handleSynchronize() {
        OpenApiService tinkoffApiService = new OpenApiService();
        tinkoffApiService.synchronizeStockInstruments();
        tinkoffApiService.synchronizeOperations();
    }

    public void initialize() {
        LOGGER.trace("Initialize Main Controller");
        Tab commonStatTab = new Tab();
        commonStatTab.setText(LocaleService.getInstance().get("tab.stat.title"));
        BorderPane tabContent = FxmlUtils.loadView(COMMON_STAT_TAB_PATH);
        commonStatTab.setContent(tabContent);
        tabPane.getTabs().add(commonStatTab);
    }

}
