package ru.indraft.service;

import ru.indraft.database.dao.InstrumentDao;
import ru.indraft.database.dao.OperationDao;
import ru.indraft.database.model.Currency;
import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;
import ru.indraft.model.CommonStatFx;
import ru.indraft.utils.MoneyUtils;
import ru.indraft.utils.OperationUtils;

import java.math.BigDecimal;
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
        addDividendRow(commonStat, operations);
        addPayInRow(commonStat, operations);
        addPayOutRow(commonStat, operations);
        return commonStat;
    }

//    private void addTotalQuantityRow(List<CommonStatFx> commonStat, List<Operation> operations) {
//        var totalQuantity = OperationUtils.getTotalQuantityOfOperations(operations);
//        var commonStatRow = new CommonStatFx();
//    }

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

    private void setCommonStatRowValues(CommonStatFx commonStatFx, BigDecimal rubValue, BigDecimal usdValue, BigDecimal eurValue) {
        commonStatFx.setValueInRub(MoneyUtils.format(
                rubValue,
                ru.indraft.model.Currency.RUB
        ));
        commonStatFx.setValueInUsd(MoneyUtils.format(
                usdValue,
                ru.indraft.model.Currency.USD
        ));
        commonStatFx.setValueInEur(MoneyUtils.format(
                eurValue,
                ru.indraft.model.Currency.EUR
        ));
    }

    private void addDividendRow(List<CommonStatFx> commonStat, List<Operation> operations) {
        var dividendOperations = OperationUtils.filterOperationsByTypes(operations, OperationType.Dividend);
        var commonStatRow = new CommonStatFx();
        commonStatRow.setParameter(LocaleService.getInstance().get("page.stat.table.parameter.dividendCommission"));
        setCommonStatRowValues(
                commonStatRow,
                OperationUtils.getTotalPaymentOfOperationsByCurrency(dividendOperations, Currency.RUB),
                OperationUtils.getTotalPaymentOfOperationsByCurrency(dividendOperations, Currency.USD),
                OperationUtils.getTotalPaymentOfOperationsByCurrency(dividendOperations, Currency.EUR)
        );
        commonStat.add(commonStatRow);
    }

    private void addPayInRow(List<CommonStatFx> commonStat, List<Operation> operations) {
        var filteredOperations = OperationUtils.filterOperationsByTypes(operations, OperationType.PayIn);
        var commonStatRow = new CommonStatFx();
        commonStatRow.setParameter(LocaleService.getInstance().get("page.stat.table.parameter.payIn"));
        setCommonStatRowValues(
                commonStatRow,
                OperationUtils.getTotalPaymentOfOperationsByCurrency(filteredOperations, Currency.RUB),
                OperationUtils.getTotalPaymentOfOperationsByCurrency(filteredOperations, Currency.USD),
                OperationUtils.getTotalPaymentOfOperationsByCurrency(filteredOperations, Currency.EUR)
        );
        commonStat.add(commonStatRow);
    }

    private void addPayOutRow(List<CommonStatFx> commonStat, List<Operation> operations) {
        var filteredOperations = OperationUtils.filterOperationsByTypes(operations, OperationType.PayOut);
        var commonStatRow = new CommonStatFx();
        commonStatRow.setParameter(LocaleService.getInstance().get("page.stat.table.parameter.payOut"));
        setCommonStatRowValues(
                commonStatRow,
                OperationUtils.getTotalPaymentOfOperationsByCurrency(filteredOperations, Currency.RUB),
                OperationUtils.getTotalPaymentOfOperationsByCurrency(filteredOperations, Currency.USD),
                OperationUtils.getTotalPaymentOfOperationsByCurrency(filteredOperations, Currency.EUR)
        );
        commonStat.add(commonStatRow);
    }


}
