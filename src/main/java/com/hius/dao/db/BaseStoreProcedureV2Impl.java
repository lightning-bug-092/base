package com.hius.dao.db;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
@Transactional
public class BaseStoreProcedureV2Impl implements BaseStoreProcedureV2 {

    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> List<T> getList(String spName, List<SPParameter> params, String rsMapping) {
        StoredProcedureQuery query = em.createStoredProcedureQuery(spName, rsMapping);

        for (SPParameter param : params) {
            switch (param.getMode()) {
                case IN:
                    query.registerStoredProcedureParameter(param.getKey(), param.getValue().getClass(), ParameterMode.IN);
                    query.setParameter(param.getKey(), param.getValue());
                    break;
                case REF_CURSOR:
                    query.registerStoredProcedureParameter(param.getKey(), param.getValue().getClass(), ParameterMode.REF_CURSOR);
                    break;
            }
        }

        query.execute();
        return query.getResultList();
    }

    @Override
    public <T> T getOne(String spName, List<SPParameter> params, String rsMapping) {
        List<T> resultList = getList(spName, params, rsMapping);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void execute(String spName, List<SPParameter> params) {
        StoredProcedureQuery query = em.createStoredProcedureQuery(spName);

        for (SPParameter param : params) {
            switch (param.getMode()) {
                case IN:
                    query.registerStoredProcedureParameter(param.getKey(), param.getValue().getClass(), ParameterMode.IN);
                    query.setParameter(param.getKey(), param.getValue());
                    break;
            }
        }

        query.execute();
    }
}
