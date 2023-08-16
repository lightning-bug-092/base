package com.hius.bean;

import com.google.common.collect.Maps;
import com.hius.utils.ObjectUtil;
import com.hius.utils.StringUtil;

import java.util.Map;

public class SearchFilter {
    public enum Operator {
        EQ, NE, LIKE, LIKEL, LIKER, GT, LT, GTE, LTE, IN, NOTIN, ISNULL, ISNOTNULL, BETWEEN, LIKEUP, LIKELO
    }

    public enum Join {
        and, or
    }

    public Join join = Join.and;
    public String fieldName;
    public Object value;
    public Operator operator;

    public static SearchFilter build(String fieldName, Object value) {
        return new SearchFilter(fieldName, Operator.EQ, value);
    }

    public static SearchFilter build(String fieldName,Operator operator) {
        return new SearchFilter(fieldName,operator);
    }
    public static SearchFilter build(String fieldName, Operator operator, Object value) {
        if (ObjectUtil.isNullOrEmpty(value) || ObjectUtil.isNullOrEmpty(fieldName))
            return null;
        return new SearchFilter(fieldName, operator, value);
    }


    public static SearchFilter build(String fieldName, Object value, Join join) {
        return new SearchFilter(fieldName, Operator.EQ, value, join);

    }
    public SearchFilter(String fieldName, Operator operator) {
        this.fieldName = fieldName;
        this.operator = operator;

    }
    public SearchFilter(String fieldName, Operator operator, Object value) {
        if (ObjectUtil.isNotNullOrEmpty(value)) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
        }
    }

    public SearchFilter(String fieldName, Operator operator, Object value, Join join) {
        if (ObjectUtil.isNotNullOrEmpty(value)) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
            this.join = join;
        }
    }


    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();

        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {

            String key = entry.getKey();
            Object value = entry.getValue();

            String[] names = StringUtil.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);

            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }

        return filters;
    }
}
