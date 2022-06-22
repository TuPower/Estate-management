package com.laptrinhjavaweb.utils;

import java.util.List;

public class SqlUtils {
    public static String buildQueryUsingBetween(String column,int form ,int to){
        return " and "+ column +" >= " + form + " and " + column +" <= " + to;
    }
    public static String buildQueryUsingOperator(String column,Object at,String compare) {
        return " and " + column + compare + "'"+at+"'";
    }
    public static String buildQueryUsingLike(String column, String value) {
        return " and " + column + " like "+ " '%" + value + "%' ";
    }
    public static String buildQueryUsingIn(String column , String [] values) {
        StringBuilder sql = new StringBuilder();
        for (String value : values) {
            sql.append(" and " + column + " like '%");
            sql.append(value+"%'");
        }
        return sql.toString();
    }
}
