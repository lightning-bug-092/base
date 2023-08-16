package com.hius.service;

public interface CrudService <T, ID> extends
        InsertService<T, ID>,
        UpdateService<T,ID>,
        DeleteService<T,ID>,
        SelectService<T, ID> {
}
