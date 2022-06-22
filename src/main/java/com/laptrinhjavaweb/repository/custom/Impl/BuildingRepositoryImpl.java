package com.laptrinhjavaweb.repository.custom.Impl;


import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.SqlUtils;
import com.laptrinhjavaweb.utils.ValidateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.laptrinhjavaweb.utils.SqlUtils.buildQueryUsingIn;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> find(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder(
                "select b.* from building b "
        );
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();
        if(builder != null) {
            builQueryWithJoin(joinQuery,whereQuery, builder);
            builQueryWithoutJoin(whereQuery, builder);
        }
        sql.append(joinQuery).append("\nwhere 1 = 1").append(whereQuery).append("\n GROUP by b.id");
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        return query.getResultList();
    }

    private void builQueryWithoutJoin(StringBuilder whereQuery, BuildingSearchBuilder builder) {
        Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
        try {
            for(Field field:fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                String column = "b." + fieldName;
                Object objectValue = field.get(builder);
                if (!fieldName.equals("district") && !fieldName.equals("types")
                        && !fieldName.startsWith("cost") && !fieldName.startsWith("area")
                        && !fieldName.equals("staffId")){
                    if (objectValue != null && objectValue != ""){
                        if(objectValue instanceof String) {
                            whereQuery.append(SqlUtils.buildQueryUsingLike(column, objectValue.toString()));
                        }if(objectValue instanceof Integer) {
                            whereQuery.append(SqlUtils.buildQueryUsingOperator(column, objectValue, "="));
                        }
                    }
                }if (fieldName.equals("district")) {
                    if(objectValue != null && objectValue != ""){
                        whereQuery.append(SqlUtils.buildQueryUsingOperator(column ,objectValue, "=" ));
                    }
                }if (fieldName.equals("types")){
                    if(objectValue != null && objectValue != ""){
                        whereQuery.append(SqlUtils.buildQueryUsingIn(column,builder.getTypes()));
                    }
                }
            }
            Integer costFrom = builder.getCostRentFrom();
            Integer costTo = builder.getCostRentTo();
            if(ValidateUtils.isValid(costFrom)&&ValidateUtils.isValid(costTo)) {
                whereQuery.append(SqlUtils.buildQueryUsingBetween("b.rentprice", costFrom, costTo));
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private void builQueryWithJoin(StringBuilder joinQuery, StringBuilder whereQuery, BuildingSearchBuilder builder) {
        Integer areaRentfrom = builder.getAreaRentFrom();
        Integer areaRentto   = builder.getAreaRentTo();
        Integer staffId      = (Integer) builder.getStaffId();
        if(ValidateUtils.isValid(areaRentfrom)&&ValidateUtils.isValid(areaRentto)) {
            joinQuery.append("\nJOIN rentarea ra ON ra.buildingid = b.id");
            whereQuery.append(SqlUtils.buildQueryUsingBetween("ra.value", areaRentfrom, areaRentto));
        }
        if(ValidateUtils.isValid(staffId)) {
            joinQuery.append("\nJOIN assignmentbuilding ab ON ab.building_id = b.id");
            whereQuery.append(SqlUtils.buildQueryUsingOperator("ab.user_id", staffId, "="));
        }

    }

}
