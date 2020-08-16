package ru.indraft.convertor;

import static ru.indraft.utils.ConvertUtils.convertEnum;

public class OperationStatusConvertor {

    public static ru.indraft.database.model.OperationStatus toDatabase(
            ru.tinkoff.invest.openapi.models.operations.OperationStatus operationStatus
    ) {
        return convertEnum(
                ru.indraft.database.model.OperationStatus.class,
                operationStatus
        );
    }

}
