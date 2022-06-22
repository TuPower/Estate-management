package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findByCustomer_Id(Long id);
    void deleteByCustomer_Id(Long id);
    void deleteByCustomer_IdIn(Long[] ids);
}
