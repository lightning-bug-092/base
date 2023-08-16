package com.hius.dao.db;


import java.util.List;

public interface BaseStoreProcedureV2 {
    <T> List<T> getList(String spName, List<SPParameter> params, String rsMapping);

    <T> T getOne(String spName, List<SPParameter> params, String rsMapping);

    void execute(String spName, List<SPParameter> params);
}
