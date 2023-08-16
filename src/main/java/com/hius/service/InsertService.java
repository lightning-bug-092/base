package com.hius.service;

public interface InsertService<T, ID> {
    T insert(T record);
}
