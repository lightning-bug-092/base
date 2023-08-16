package com.hius.dao.db;

import org.hibernate.transform.ResultTransformer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EntityResultTransformer<T> implements ResultTransformer {

    private final Class<T> resultClass;

    public EntityResultTransformer(Class<T> resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public T transformTuple(Object[] tuple, String[] aliases) {
        try {
            T entity = resultClass.getDeclaredConstructor().newInstance();
            for (int i = 0; i < aliases.length; i++) {
                // Assuming the aliases correspond to entity field names
                Field field = resultClass.getDeclaredField(aliases[i]);
                field.setAccessible(true);
                field.set(entity, tuple[i]);
            }
            return entity;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException |
                 IllegalArgumentException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException("Error mapping result to entity", e);
        }
    }

    @Override
    public List transformList(List collection) {
        return collection;
    }
}