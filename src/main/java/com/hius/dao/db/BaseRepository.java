package com.hius.dao.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>
        , PagingAndSortingRepository<T, ID>
        , JpaSpecificationExecutor<T>
        , SPRepository<T,ID> {

    List<Map> queryBySql(String sql);

    List<?> queryBySql(String sql,Class<?> klass);

    List<Map> queryMapBySql(String sql);

    List<?> queryObjBySql(String sql, Class<?> klass);

    List<T> query(String sql);

    Object getBySql(String sql,Class<?> klass);

    T get(String sql);

    T getOne(ID id);

    int execute(String sql);

    Class<T> getDataClass();
}
