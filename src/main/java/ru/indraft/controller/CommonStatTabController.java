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
import ru.indraft.model.CommonStatFx;
import ru.indraft.model.Currency;
import ru.indraft.service.CommonStatService;
import ru.indraft.service.LocaleService;
import ru.indraft.utils.MoneyUtils;

import java.util.List;

public class CommonStatTabController implements IPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonStatTabController.class);

    private final ObservableList<CommonStatFx> commonStatFxObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<CommonStatFx> statTableView;
    @FXML
    private TableColumn<CommonStatFx, String> parameterColumn;
    @FXML
    public TableColumn<CommonStatFx, String> valueInRubColumn;
    @FXML
    public TableColumn<CommonStatFx, String> valueInUsdColumn;
    @FXML
    public TableColumn<CommonStatFx, String> valueInEurColumn;

    private void loadCommonStat() {
        var dao = new OperationDao();
        var operations = dao.queryForAll();
        LOGGER.info("MARGIN COMMISSION: {}", CommonStatService.getMarginCommissionSum(operations));
        populateTable(operations);
    }

    private void populateTable(List<Operation> operations) {
        commonStatFxObservableList.clear();
        CommonStatFx marginCommission = new CommonStatFx();
        marginCommission.setParameter(LocaleService.getInstance().get("page.stat.table.parameter.marginCommission"));
        marginCommission.setValue(MoneyUtils.format(CommonStatService.getMarginCommissionSum(operations), Currency.RUB));
        commonStatFxObservableList.add(marginCommission);
        statTableView.setItems(commonStatFxObservableList);
    }

    @FXML
    public void refresh() {
        loadCommonStat();
    }

    public void initialize() {
        initColumns();
        loadCommonStat();
    }

    private void initColumns() {
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().parameterProperty());
        valueInRubColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        valueInUsdColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        valueInEurColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
    }

}
