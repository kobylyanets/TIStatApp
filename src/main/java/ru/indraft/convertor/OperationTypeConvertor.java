package ru.indraft.convertor;

public class OperationTypeConvertor {

    public static ru.indraft.database.model.OperationType convertToDatabase(
            ru.tinkoff.invest.openapi.models.operations.OperationType operationType
    ) {
        try {
            return ru.indraft.database.model.OperationType.valueOf(operationType.name());
        } catch (IllegalArgumentException throwables) {
            throwables.printStackTrace();
            return ru.indraft.database.model.OperationType.Unknown;
        } catch (NullPointerException nullPointerException) {
            return ru.indraft.database.model.OperationType.Unknown;
        }
    }

}
