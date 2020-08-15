package ru.indraft.convertor;

public class OperationStatusConvertor {

    public static ru.indraft.database.model.OperationStatus convertToDatabase(
            ru.tinkoff.invest.openapi.models.operations.OperationStatus operationStatus
    ) {
        try {
            return ru.indraft.database.model.OperationStatus.valueOf(operationStatus.name());
        } catch (IllegalArgumentException throwables) {
            throwables.printStackTrace();
            return ru.indraft.database.model.OperationStatus.Unknown;
        }
    }

}
