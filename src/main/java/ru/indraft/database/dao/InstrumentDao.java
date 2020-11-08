package ru.indraft.database.dao;

import ru.indraft.database.model.Instrument;

public class InstrumentDao extends CommonDao<Instrument> {
    public InstrumentDao() {
        super(Instrument.class);
    }
}
