package ru.indraft.service;

import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.database.model.Currency;
import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;
import ru.indraft.model.CommonStatFx;
import ru.indraft.utils.MoneyUtils;
import ru.indraft.utils.OperationUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonStatService {

    private final InstrumentDao instrumentDao = new InstrumentDao();
    private final OperationDao operationDao = new OperationDao();

    public List<CommonStatFx> getCommonStat() {
        var operations = operationDao.queryForAll();
        var instruments = instrumentDao.queryForAll();
        var commonStat = new ArrayList<CommonStatFx>();
        addMarginalCommission(commonStat, operations);
        addServiceCommission(commonStat, operations);
        return commonStat;
    }

    private void addTotalQuantityRow(List<CommonStatFx> commonStat, List<Operation> operations) {
        var totalQuantity = OperationUtils.getTotalQuantityOfOperations(operations);
        var commonStatRow = new CommonStatFx();
    }

    private void addMarginalCommission(List<CommonStatFx> commonStat, List<Operation> operations) {
        var marginCommissionOperations =
                OperationUtils.filterOperationsByTypes(operations, OperationType.MarginCommission);
        var commonStatRow = new CommonStatFx();
        commonStatRow.setParameter(LocaleService.getInstance().get("page.stat.table.parameter.marginCommission"));

        commonStatRow.setValueInRub(
                MoneyUtils.format(
                        OperationUtils.getTotalPaymentOfOperationsByCurrency(marginCommissionOperations, Currency.RUB),
                        ru.indraft.model.Currency.RUB
                )
        );
        commonStatRow.setValueInEur(
                MoneyUtils.format(
                        OperationUtils.getTotalPaymentOfOperationsByCurrency(marginCommissionOperations, Currency.EUR),
                        ru.indraft.model.Currency.EUR
                )
        );
        commonStatRow.setValueInUsd(
                MoneyUtils.format(
                        OperationUtils.getTotalPaymentOfOperationsByCurrency(marginCommissionOperations, Currency.USD),
                        ru.indraft.model.Currency.USD
                )
        );
        commonStat.add(commonStatRow);
    }

    private void addServiceCommission(List<CommonStatFx> commonStat, List<Operation> operations) {
        var serviceCommissionOperations =
                OperationUtils.filterOperationsByTypes(operations, OperationType.ServiceCommission);
        var commonStatRow = new CommonStatFx();
        commonStatRow.setParameter(LocaleService.getInstance().get("page.stat.table.parameter.serviceCommission"));

        commonStatRow.setValueInRub(
                MoneyUtils.format(
                        OperationUtils.getTotalPaymentOfOperationsByCurrency(serviceCommissionOperations, Currency.RUB),
                        ru.indraft.model.Currency.RUB
                )
        );
        commonStatRow.setValueInEur(
                MoneyUtils.format(
                        OperationUtils.getTotalPaymentOfOperationsByCurrency(serviceCommissionOperations, Currency.EUR),
                        ru.indraft.model.Currency.EUR
                )
        );
        commonStatRow.setValueInUsd(
                MoneyUtils.format(
                        OperationUtils.getTotalPaymentOfOperationsByCurrency(serviceCommissionOperations, Currency.USD),
                        ru.indraft.model.Currency.USD
                )
        );
        commonStat.add(commonStatRow);
    }

}
