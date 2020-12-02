package ru.indraft.utils;

import ru.indraft.database.model.Currency;
import ru.indraft.database.model.Operation;
import ru.indraft.database.model.OperationType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperationUtils {

    public static List<Operation> filterOperationsByTypes(List<Operation> operations, OperationType... operationTypes) {
        return operations.stream()
                .filter(
                        operation -> Arrays.stream(operationTypes)
                                .anyMatch(
                                        operationType -> operation.getOperationType() == operationType
                                )
                )
                .collect(Collectors.toList());
    }

    public static List<Operation> filterOperationsByCurrency(List<Operation> operations, Currency currency) {
        return operations.stream()
                .filter(operation -> operation.getCurrency() == currency)
                .collect(Collectors.toList());
    }

    public static Integer getTotalQuantityOfOperations(List<Operation> operations) {
        return operations.stream()
                .map(Operation::getQuantity)
                .reduce(0, Integer::sum);
    }

    public static BigDecimal getTotalPaymentOfOperations(List<Operation> operations) {
        return operations.stream()
                .map(Operation::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .abs();
    }

    public static BigDecimal getTotalPaymentOfOperationsByCurrency(List<Operation> operations, Currency currency) {
        var filteredOperations = filterOperationsByCurrency(operations, currency);
        return filteredOperations.stream()
                .map(Operation::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .abs();
    }

}
