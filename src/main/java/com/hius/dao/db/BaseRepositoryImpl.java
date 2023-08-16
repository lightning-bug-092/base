package com.hius.dao.db;

import com.google.common.collect.Lists;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.nutz.mapl.Mapl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    private final EntityManager entityManager;
    private Class<T> klass;


    BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
                       EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.klass = (Class<T>) entityInformation.getJavaType();
    }

    @Override
    public List<Map> queryBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        return list;
    }
    @Override
    public List<Map> queryMapBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        return list;
    }
    @Override
    public List<?> queryBySql(String sql, Class<?> klass) {
        List<Map> list = queryBySql(sql);
        if(list.isEmpty()){
            return null;
        }
        List result = Lists.newArrayList();
        for(Map map :list){
            try {
                Object bean = Mapl.maplistToObj(map,klass);
                result.add(bean);
            }catch (Exception e){
            }
        }
        return result;
    }
    @Override
    public List<?> queryObjBySql(String sql, Class<?> klass) {
        List<Map> list = queryMapBySql(sql);
        if(list.isEmpty()){
            return Lists.newArrayList();
        }
        List result = Lists.newArrayList();
        for(Map map:list){
            try {
                Object bean = Mapl.maplistToObj(map, klass);
                result.add(bean);
            } catch (Exception e) {
            }
        }
        return result ;

    }
    @Override
    public T getOne(ID id){
        return findById(id).get();
    }
    @Override
    public T get(String sql) {
        List<T> list =  entityManager.createNativeQuery(sql,klass).getResultList();
        return list.get(0);
    }

    @Override
    public int execute(String sql) {
        return entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Override
    public Class<T> getDataClass() {
        return klass;
    }

    @Override
    public List<T> query(String sql) {
        return entityManager.createNativeQuery(sql,klass).getResultList();
    }

    @Override
    public Object getBySql(String sql, Class<?> klass) {
        List list = queryBySql(sql,klass);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<T> getListUsingSP(String spName, Map<String, Object> parameters, Class<T> kclass) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(spName);

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    @Override
    public T getOneUsingSP(String spName, Map<String, Object> parameters, Class<T> kclass) {
        List<T> resultList = getListUsingSP(spName, parameters, kclass);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}

