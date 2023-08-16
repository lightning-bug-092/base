package com.hius.dao.db;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface SPRepository<T, ID extends Serializable> {

    List<T> getListUsingSP(String spName, Map<String, Object> parameters, Class<T> entityClass);

    T getOneUsingSP(String spName, Map<String, Object> parameters, Class<T> entityClass);
}
