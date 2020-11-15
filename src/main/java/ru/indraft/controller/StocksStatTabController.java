package ru.indraft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.database.model.Operation;
import ru.indraft.model.StockStatFx;
import ru.indraft.service.CommonStatService;

import java.util.List;

public class StocksStatTabController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StocksStatTabController.class);
    private final ObservableList<StockStatFx> operationsStatFxObservableList = FXCollections.observableArrayList();

    public TableView<StockStatFx> operationsStatTableView;
    public TableColumn<StockStatFx, String> tickerColumn;
    public TableColumn<StockStatFx, String> instrumentNameColumn;
    public TableColumn<StockStatFx, String> profitColumn;

    private void populateTable(List<Operation> operations) {
        operationsStatFxObservableList.clear();
        operationsStatFxObservableList.addAll(CommonStatService.getStockStatOperations(operations));
        operationsStatTableView.setItems(operationsStatFxObservableList);
    }

    private void loadCommonStat() {
        var dao = new OperationDao();
        var operations = dao.queryForAll();
        populateTable(operations);
    }

    @FXML
    private void refresh() {
        loadCommonStat();
    }

    public void initialize() {
        initColumns();
        loadCommonStat();
    }

    private void initColumns() {
        tickerColumn.setCellValueFactory(cellData -> cellData.getValue().tickerProperty());
        instrumentNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        profitColumn.setCellValueFactory(cellData -> cellData.getValue().profitProperty());
    }


}
