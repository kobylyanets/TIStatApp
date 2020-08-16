package ru.indraft.convertor;

import ru.indraft.database.model.Instrument;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OperationConvertor {

    public static List<ru.indraft.database.model.Operation> convertToDatabase(
            List<ru.tinkoff.invest.openapi.models.operations.Operation> operations
    ) {
        List<ru.indraft.database.model.Operation> operationsDb = new ArrayList<>();
        for (var operation : operations) {
            operationsDb.add(convertToDatabase(operation));
        }
        return operationsDb;
    }

    public static ru.indraft.database.model.Operation convertToDatabase(
            ru.tinkoff.invest.openapi.models.operations.Operation operation
    ) {
        ru.indraft.database.model.Operation operationDb = new ru.indraft.database.model.Operation();
        operationDb.setId(operation.id);

        operationDb.setStatus(OperationStatusConvertor.toDatabase(operation.status));

        operationDb.setCurrency(CurrencyConvertor.toDatabase(operation.currency));

        operationDb.setPayment(operation.payment);

        operationDb.setPrice(operation.price);

        operationDb.setQuantity(operation.quantity);

        Instrument instrumentDb = new Instrument();
        instrumentDb.setFigi(operation.figi);
        operationDb.setInstrument(instrumentDb);

        operationDb.setInstrumentType(InstrumentTypeConvertor.toDatabase(operation.instrumentType));

        operationDb.setMarginCall(operation.isMarginCall);

        operationDb.setDate(Date.from(operation.date.toInstant()));

        operationDb.setOperationType(OperationTypeConvertor.toDatabase(operation.operationType));

        return operationDb;
    }

}
