package com.hius.bean;

public class GeneratedCriteria {

    private final StringBuilder fromBuilder = new StringBuilder();

    protected GeneratedCriteria(String tableName, String asName) {
        super();
        fromBuilder.append("FROM ")
                .append(tableName)
                .append(" ")
                .append(asName);
    }


    private final StringBuilder joinBuilder = new StringBuilder();

    public Criteria join(String joinFunc, String tableName, String asName, String condition) {
        joinBuilder.append(joinFunc)
                .append(" ")
                .append(tableName)
                .append(" ")
                .append(asName)
                .append(" ON ")
                .append(condition);
        return (Criteria) this;
    }

    private final StringBuilder selectBuilder = new StringBuilder("SELECT ");

    public Criteria select(String colum) {
        selectBuilder.append(colum + ",");
        return (Criteria) this;
    }

    private final StringBuilder whereBuilder = new StringBuilder("");

    public Criteria like(String colum,Object object) {
        if (object!=null){
            if (whereBuilder.toString().contains("WHERE")){
                whereBuilder.append(" AND ");
            }else {
                whereBuilder.append(" WHERE ");
            }
            whereBuilder.append(colum)
                    .append(" LIKE '%")
                    .append(object)
                    .append("%'");
        }
        return (Criteria) this;
    }
    public Criteria greaterThan(String colum,Object object) {
        if (object!=null){
            if (whereBuilder.toString().contains("WHERE")){
                whereBuilder.append(" AND ");
            }else {
                whereBuilder.append(" WHERE ");
            }
            whereBuilder.append(colum)
                    .append(" >= ")
                    .append(object);
        }
        return (Criteria) this;
    }

    public Criteria lessThan(String colum,Object object) {
        if (object!=null){
            if (whereBuilder.toString().contains("WHERE")){
                whereBuilder.append(" AND ");
            }else {
                whereBuilder.append(" WHERE ");
            }
            whereBuilder.append(colum)
                    .append(" <= ")
                    .append(object);
        }
        return (Criteria) this;
    }
    public Criteria equal(String colum,Object object) {
        if (object!=null){
            if (whereBuilder.toString().contains("WHERE")){
                whereBuilder.append(" AND ");
            }else {
                whereBuilder.append(" WHERE ");
            }
            whereBuilder.append(colum)
                    .append(" = ")
                    .append(object);

        }
        return (Criteria) this;
    }
    public Criteria between(String colum,String object1, String  object2) {
        if (object1!=null && object2!=null){
            if (whereBuilder.toString().contains("WHERE")){
                whereBuilder.append(" AND ");
            }else {
                whereBuilder.append(" WHERE ");
            }
            whereBuilder.append("(")
                    .append(colum)
                    .append(" BETWEEN '")
                    .append(object1)
                    .append("'")
                    .append(" AND '")
                    .append( object2 )
                    .append("')");

        }
        return (Criteria) this;
    }

    private final StringBuilder limitBuilder = new StringBuilder();

    public Criteria limitAndOffset(int limit, int offset) {
        limitBuilder.append(" LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);
        return (Criteria) this;
    }
    private final StringBuilder orderBuilder = new StringBuilder();

    public Criteria orderBy(String keySort, String order) {
        orderBuilder.append(" ORDER BY ")
                .append(keySort)
                .append(" ")
                .append(order);
        return (Criteria) this;
    }


    public String getSql() {
        StringBuilder builder = new StringBuilder();
        builder.append(selectBuilder.deleteCharAt(selectBuilder.length() - 1)).append(" ")
                .append(fromBuilder).append(" ")
                .append(joinBuilder).append(" ")
                .append(whereBuilder).append(" ")
                .append(orderByBuilder).append(" ")
                .append(limitBuilder);
        return builder.toString();
    }
}
