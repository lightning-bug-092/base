package com.hius.service;


import com.hius.bean.*;
import com.hius.dao.db.BaseRepository;
import com.hius.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.*;

public abstract class BaseService<T, ID extends Serializable, R extends BaseRepository<T, ID>>
        implements CrudService<T, ID>{
    @Autowired
    private R dao;

    @Override
    public void delete(T record) {
        dao.delete(record);
    }

    @Override
    public void deleteById(ID id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(Iterable<ID> ids) {
        Iterator<ID> iterator = ids.iterator();
        while (iterator.hasNext()) {
            ID id = iterator.next();
            dao.deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<T> list) {
        dao.deleteInBatch(list);
    }

    @Override
    public T insert(T record) {
        return dao.save(record);
    }

    @Override
    public T get(ID id) {
        Optional<T> o = dao.findById(id);
        return o.isPresent() ? o.get() : null;
    }

    @Override
    public T get(SearchFilter filter) {
        List<T> list = queryAll(filter);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public T get(List<SearchFilter> filters) {
        List<T> list = queryAll(filters);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Object get(String sql, Class<?> klass) {
        return dao.getBySql(sql, klass);
    }

    @Override
    public List<T> query(Iterable<ID> ids) {
        return dao.findAllById(ids);
    }

    @Override
    public List<T> queryAll() {
        return dao.findAll();
    }

    @Override
    public List<Map> queryBySql(String sql) {
        return dao.queryBySql(sql);
    }

    @Override
    public Map getMapBySql(String sql) {
        List<Map> list = queryBySql(sql);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Page<T> queryPage(Page<T> page) {
        Pageable pageable = null;
        if (page.getSort() != null) {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), page.getSort());
        } else {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC, "id");
        }
        Specification<T> specification = DynamicSpecifications.bySearchFilter(page.getFilters(), dao.getDataClass());
        org.springframework.data.domain.Page<T> pageResult = dao.findAll(specification, pageable);
        page.setTotal(Integer.valueOf(pageResult.getTotalElements() + ""));
        page.setRecords(pageResult.getContent());
        return page;
    }

    public Page<T> queryPage(Page<T> page, String... idNames) {
        Pageable pageable = null;
        if (page.getSort() != null) {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), page.getSort());
        } else {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC, idNames);
        }
        Specification<T> specification = DynamicSpecifications.bySearchFilter(page.getFilters(), dao.getDataClass());
        org.springframework.data.domain.Page<T> pageResult = dao.findAll(specification, pageable);
        page.setTotal(Integer.valueOf(pageResult.getTotalElements() + ""));
        page.setRecords(pageResult.getContent());
        return page;
    }

    @Override
    public List<T> queryAll(List<SearchFilter> filters) {
        return queryAll(filters, null);
    }

    @Override
    public List<T> queryAll(SearchFilter filter) {
        return queryAll(filter, null);
    }

    @Override
    public List<T> queryAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public List<T> queryAll(List<SearchFilter> filters, Sort sort) {
        Specification<T> specification = DynamicSpecifications.bySearchFilter(filters, dao.getDataClass());
        if (sort == null) {
            return dao.findAll(specification);
        }
        return dao.findAll(specification, sort);
    }

    @Override
    public List<T> queryAll(SearchFilter filter, Sort sort) {
        if (filter != null) {
            return queryAll(Lists.newArrayList(filter), sort);
        } else {
            return queryAll(new ArrayList(), sort);
        }

    }

    @Override
    public long count(SearchFilter filter) {
        return count(Lists.newArrayList(filter));
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public long count(List<SearchFilter> filters) {
        Specification<T> specification = DynamicSpecifications.bySearchFilter(filters, dao.getDataClass());
        return dao.count(specification);
    }

    @Override
    public T update(T record) {
        return dao.save(record);
    }

    @Override
    public void update(Iterable<T> list) {
        dao.saveAll(list);
    }

    @Override
    public void clear() {
        dao.deleteAllInBatch();
    }

    public <T> List<T> queryByCriteria(Criteria criteria, MapperObject mapperObject) {
        String sql = criteria.getSql();
        if (sql != null && StringUtil.isNotEmpty(sql.trim())) {
            List<Map> resultQuery = this.queryBySql(sql);
            List<T> res = new ArrayList<>();
            for (Map input : resultQuery) {
                res.add((T) mapperObject.mapper(input));
            }
            return res;
        }
        return new ArrayList<>();
    }
}
