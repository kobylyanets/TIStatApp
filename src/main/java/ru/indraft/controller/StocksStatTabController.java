package ru.indraft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.model.StockStatFx;
import ru.indraft.service.StocksStatService;

import java.util.ArrayList;
import java.util.List;

public class StocksStatTabController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StocksStatTabController.class);

    private final ObservableList<StockStatFx> stocksStatFxObservableList = FXCollections.observableArrayList();

    private final StocksStatService stocksStatService = new StocksStatService();
    private List<StockStatFx> stocksStat = new ArrayList<>();

    @FXML
    public TableView<StockStatFx> stocksStatTableView;
    @FXML
    public TableColumn<StockStatFx, String> tickerColumn;
    @FXML
    public TableColumn<StockStatFx, String> nameColumn;
    @FXML
    public TableColumn<StockStatFx, String> profitColumn;

    @FXML
    private void refresh() {
        loadStocksStat();
    }

    @FXML
    public void initialize() {
        initColumns();
        loadStocksStat();
    }

    private void initColumns() {
        tickerColumn.setCellValueFactory(cellData -> cellData.getValue().tickerProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        profitColumn.setCellValueFactory(cellData -> cellData.getValue().profitProperty());
    }

    private void loadStocksStat() {
        stocksStat = stocksStatService.getStocksStat();
        populateTable(stocksStat);
    }

    private void populateTable(List<StockStatFx> stocksStat) {
        stocksStatFxObservableList.clear();
        stocksStatFxObservableList.addAll(stocksStat);
        stocksStatTableView.setItems(stocksStatFxObservableList);
    }

}
