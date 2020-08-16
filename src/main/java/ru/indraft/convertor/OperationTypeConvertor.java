package ru.indraft.convertor;

import static ru.indraft.utils.ConvertUtils.convertEnum;

public class OperationTypeConvertor {

    public static ru.indraft.database.model.OperationType toDatabase(
            ru.tinkoff.invest.openapi.models.operations.OperationType operationType
    ) {
        return convertEnum(
                ru.indraft.database.model.OperationType.class,
                operationType
        );
    }

}
