package ru.indraft.service;

import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CommonStatService {

    public static Double getMarginCommissionSum(List<Operation> operations) {
        return operations.stream()
                .filter(operation -> operation.getOperationType() == OperationType.MarginCommission)
                .map(Operation::getPayment)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }

}
