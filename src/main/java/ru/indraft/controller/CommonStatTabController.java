package ru.indraft.controller;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.service.CommonStatService;

public class CommonStatTabController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonStatTabController.class);

    private void loadCommonStat() {
        var dao = new OperationDao();
        var operations = dao.queryForAll();
        LOGGER.info("MARGIN COMMISSION: {}", CommonStatService.getMarginCommission(operations));
    }

    @FXML
    private void refresh() {
        loadCommonStat();
    }

    public void initialize() {
        loadCommonStat();
    }

}
