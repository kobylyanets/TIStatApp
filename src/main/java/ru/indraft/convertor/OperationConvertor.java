package ru.indraft.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.database.model.Instrument;

import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OperationConvertor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationConvertor.class);

    public static List<ru.indraft.database.model.Operation> convertToDatabase(
            List<ru.tinkoff.invest.openapi.models.operations.Operation> operations
    ) {
        List<ru.indraft.database.model.Operation> operationsDb = new ArrayList<>();
        for (var operation : operations) {
            operationsDb.add(convertToDatabase(operation));
        }
        return operationsDb;
    }

    /**
     * Дефект в api,
     * что возвращается неверное количество позиций для некоторых операций,
     * поэтому вычисляем правильнок количество позиций делением общей суммы на цену бумаги.
     *
     * @link https://github.com/TinkoffCreditSystems/invest-openapi-java-sdk/issues/107
     */
    private static Integer calcQuantity(ru.tinkoff.invest.openapi.models.operations.Operation operation) {
        if (operation.price != null) {
            return operation.payment.divide(operation.price, 0, RoundingMode.HALF_UP).abs().intValue();
        }
        return null;
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

        operationDb.setQuantity(calcQuantity(operation));

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
