package com.hius.service;

public interface UpdateService <T, ID> {
    T update(T record);
    void update(Iterable<T> list);
}