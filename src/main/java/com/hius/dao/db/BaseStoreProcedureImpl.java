package com.hius.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class BaseStoreProcedureImpl implements BaseStoreProcedure {

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public <T> List<T> getList(String spName, Map<String, Object> params, String spOut, Class<T> dtoClass) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(spName)
                .returningResultSet(spOut,
                        BeanPropertyRowMapper.newInstance(dtoClass));

        Map out = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(params));

        if (out.isEmpty()) {
            return Collections.emptyList();
        } else {
            return (List<T>) out.get(spOut);
        }
    }

    @Override
    public <T> T getOne(String spName, Map<String, Object> params, String spOut, Class<T> dtoClass) {
        List<T> resultList = getList(spName, params, spOut, dtoClass);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public <T> T getSingleResult(String spName, Map<String, Object> params, String outputParamName) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(spName);

        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(params);

        Map out = simpleJdbcCall.execute(in);
        if (!out.isEmpty()) {
            return (T) out.get(outputParamName);
        } else {
            return null;
        }
    }

    @Override
    public void execute(String spName, Map<String, Object> params) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(spName);

        SqlParameterSource in = new MapSqlParameterSource()
                .addValues(params);

        // Execute the Stored Procedure
        simpleJdbcCall.execute(in);

    }

}
