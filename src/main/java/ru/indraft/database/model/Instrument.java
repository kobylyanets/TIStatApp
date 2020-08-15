package ru.indraft.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "INSTRUMENT")
public class Instrument implements BaseModel {
    @DatabaseField(id = true, columnName = "FIGI")
    private String figi;
    @DatabaseField(columnName = "TICKER")
    private String ticker;
    @DatabaseField
    private String isin;
    @DatabaseField
    private String name;
    @DatabaseField(unknownEnumName = "Unknown")
    private InstrumentType type;
    @DatabaseField(unknownEnumName = "UNKNOWN")
    private Currency currency;
}
