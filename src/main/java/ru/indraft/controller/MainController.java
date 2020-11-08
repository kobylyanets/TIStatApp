package ru.indraft.controller;

import javafx.fxml.FXML;
import ru.indraft.service.OpenApiService;

public class MainController {

    @FXML
    private void handleSynchronize() {
        OpenApiService tinkoffApiService = new OpenApiService();
        tinkoffApiService.synchronizeStockInstruments();
        tinkoffApiService.synchronizeOperations();
    }

}
