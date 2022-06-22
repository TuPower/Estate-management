package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    List<CustomerEntity> findByNameContainsAndPhoneContainsAndEmailContains(String name,String phone ,String email);
    List<CustomerEntity> findByNameContainsAndPhoneContainsAndEmailContainsAndUsers_Id(String name,String phone ,String email,Long id);
    List<CustomerEntity> findByIdIn(List<Long> ids);
    void deleteByIdIn(Long [] ids);
}
