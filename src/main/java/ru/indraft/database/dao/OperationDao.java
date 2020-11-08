package ru.indraft.database.dao;

import ru.indraft.database.model.Operation;

public class OperationDao extends CommonDao<Operation> {
    public OperationDao() {
        super(Operation.class);
    }
}
