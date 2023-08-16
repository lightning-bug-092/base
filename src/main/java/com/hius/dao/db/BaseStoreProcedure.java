package com.hius.dao.db;

import java.util.List;
import java.util.Map;

public interface BaseStoreProcedure {
    <T> List<T> getList(String spName, Map<String, Object> params, String spOut, Class<T> dtoClass);

    <T> T getOne(String spName, Map<String, Object> params, String spOut, Class<T> dtoClass);

    <T> T getSingleResult(String spName, Map<String, Object> params, String outputParamName);

    void execute(String spName, Map<String, Object> params);

}
