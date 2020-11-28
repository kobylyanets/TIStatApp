package ru.indraft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.model.Currency;
import ru.indraft.model.StockStatFx;
import ru.indraft.service.StocksStatService;
import ru.indraft.utils.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StocksStatTabController implements IPageController {

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
    public TableColumn<StockStatFx, String> commissionColumn;
    @FXML
    public TableColumn<StockStatFx, String> dividendColumn;
    @FXML
    public TableColumn<StockStatFx, String> profitColumn;
    @FXML
    public TableColumn<StockStatFx, String> totalColumn;
    @FXML
    public ToggleGroup tgCurrencyFilter;
    @FXML
    public RadioButton clearCurrencyFilter;
    @FXML
    public TextField searchByTickerTextField;
    @FXML
    public Label totalSumInUSDLabel;
    @FXML
    public Label totalSumInRUBLabel;

    @FXML
    public void refresh() {
        LOGGER.info("REFRESH {}", System.currentTimeMillis());
        clearCurrencyFilter.setSelected(true);
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
        commissionColumn.setCellValueFactory(cellData -> cellData.getValue().commissionProperty());
        dividendColumn.setCellValueFactory(cellData -> cellData.getValue().dividendProperty());
        profitColumn.setCellValueFactory(cellData -> cellData.getValue().profitProperty());
        totalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
    }

    private void loadStocksStat() {
        stocksStat = stocksStatService.getStocksStat();
        populateTable(stocksStat);
        calcTotalSums();
    }

    private void calcTotalSums() {
        var totalSumInUSD =
                stocksStatService.getTotalSumByCurrency(stocksStat, Currency.USD);
        totalSumInUSDLabel.setText(MoneyUtils.format(totalSumInUSD, Currency.USD));
        var totalSumInRUB =
                stocksStatService.getTotalSumByCurrency(stocksStat, Currency.RUB);
        totalSumInRUBLabel.setText(MoneyUtils.format(totalSumInRUB, Currency.RUB));
    }

    private void populateTable(List<StockStatFx> stocksStat) {
        stocksStatFxObservableList.clear();
        stocksStatFxObservableList.addAll(stocksStat);
        stocksStatTableView.setItems(stocksStatFxObservableList);
    }

    private List<StockStatFx> filterByCurrency(List<StockStatFx> stocksStat, Currency currency) {
        return stocksStat.stream()
                .filter(stockStat -> stockStat.getCurrencyParam() == currency)
                .collect(Collectors.toList());
    }

    @FXML
    public void handleRubCurrencyFilter() {
        var rubStocksStat = filterByCurrency(stocksStat, Currency.RUB);
        populateTable(rubStocksStat);
    }

    @FXML
    public void handleUsdCurrencyFilter() {
        var usdStocksStat = filterByCurrency(stocksStat, Currency.USD);
        populateTable(usdStocksStat);
    }

    @FXML
    public void handleClearCurrencyFilter() {
        populateTable(stocksStat);
    }

    private void filterByTicker(List<StockStatFx> stocksStat, String ticker) {
        var filteredStocksStat = stocksStat.stream()
                .filter(
                        stockStat ->
                                stockStat.getTicker()
                                        .startsWith(
                                                ticker.toUpperCase().trim()
                                        )
                ).collect(Collectors.toList());
        populateTable(filteredStocksStat);
    }

    @FXML
    public void handleSearchByTicker() {
        var ticker = searchByTickerTextField.getText();
        filterByTicker(stocksStat, ticker);
    }

    public void onKeyPressedSearchByTicker(KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getCode() == KeyCode.ENTER) {
            handleSearchByTicker();
        }
    }
}
