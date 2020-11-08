package ru.indraft.controller;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class CommonStatTabController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonStatTabController.class);

    private void loadCommonStat() {
        var dao = new OperationDao();
        var list = dao.queryForAll(Operation.class);
        LOGGER.info("size: {}",list.size());
        var margin = list.stream().filter(operation -> operation.getOperationType() == OperationType.MarginCommission).collect(Collectors.toList());
        LOGGER.info("margin: {}", margin);
        margin.stream().map(Operation::getPayment).reduce(BigDecimal.ZERO, BigDecimal::add).toPlainString();
    }

    @FXML
    private void refresh() {
        loadCommonStat();
    }

    public void initialize() {
        loadCommonStat();
    }

}
