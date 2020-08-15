package ru.indraft.database.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@DatabaseTable(tableName = "OPERATION")
public class Operation implements BaseModel {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField(unknownEnumName = "Unknown")
    private OperationStatus status;

    @DatabaseField(unknownEnumName = "UNKNOWN")
    private Currency currency;

    @DatabaseField(dataType = DataType.BIG_DECIMAL_NUMERIC)
    private BigDecimal payment;

    @DatabaseField(dataType = DataType.BIG_DECIMAL_NUMERIC)
    private BigDecimal price;

    @DatabaseField
    private Integer quantity;

    @DatabaseField(columnName = "figi", foreign = true, foreignAutoRefresh = true)
    private Instrument instrument;

    @DatabaseField(unknownEnumName = "Unknown")
    private InstrumentType instrumentType;

    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isMarginCall;

    @DatabaseField(dataType = DataType.DATE)
    private Date date;

    @DatabaseField(unknownEnumName = "Unknown")
    private OperationType operationType;
}
