package ru.indraft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.model.CommonStatFx;
import ru.indraft.service.CommonStatService;

import java.util.ArrayList;
import java.util.List;

public class CommonStatTabController implements IPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonStatTabController.class);

    private final ObservableList<CommonStatFx> commonStatFxObservableList = FXCollections.observableArrayList();

    private final CommonStatService commonStatService = new CommonStatService();
    private List<CommonStatFx> commonStat = new ArrayList<>();

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
        commonStat = commonStatService.getCommonStat();
        populateTable(commonStat);
    }

    private void populateTable(List<CommonStatFx> commonStat) {
        commonStatFxObservableList.clear();
        commonStatFxObservableList.addAll(commonStat);
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
        valueInRubColumn.setCellValueFactory(cellData -> cellData.getValue().valueInRubProperty());
        valueInUsdColumn.setCellValueFactory(cellData -> cellData.getValue().valueInUsdProperty());
        valueInEurColumn.setCellValueFactory(cellData -> cellData.getValue().valueInEurProperty());
    }

}
