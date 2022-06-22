package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchResquestDTO;
import com.laptrinhjavaweb.dto.request.TransactionDTOResquest;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<CustomerResponseDTO> find(CustomerSearchResquestDTO dto){
        CustomerSearchResquestDTO customer = customerConverter.convertToDTO(dto);
        List<CustomerEntity> entities = new ArrayList<>();
        if (customer.getStaffId()== null){
            entities = customerRepository.findByNameContainsAndPhoneContainsAndEmailContains(
                    customer.getName(),customer.getPhone(),customer.getEmail()
            );
        }else {
            entities = customerRepository.findByNameContainsAndPhoneContainsAndEmailContainsAndUsers_Id(
                    customer.getName(),customer.getPhone(),customer.getEmail(),customer.getStaffId()
            );
        }
        List<CustomerResponseDTO> results = entities.stream()
                .map(item -> customerConverter.convertToDTO(item))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    @Transactional
    public void save(CustomerDTO dto) {
        Long customer_id = dto.getId();
        CustomerEntity customerEntity = customerConverter.convertToEntity(dto);
        if (customer_id != null){
            transactionRepository.deleteByCustomer_Id(customer_id);
            customerEntity.setUsers(userRepository.findByCustomers_Id(customer_id));
        }
        customerRepository.save(customerEntity);
    }


    @Override
    public CustomerDTO findOneById(Long id) {
        return customerConverter.convertToDTOUpdate(customerRepository.findOne(id));
    }

    @Override
    @Transactional
    public void delete(Long [] ids) {
        if(ids != null){
            transactionRepository.deleteByCustomer_IdIn(ids);
            customerRepository.deleteByIdIn(ids);
        }
    }

    @Override
    @Transactional
    public void addTransaction(TransactionDTOResquest dto) {
        CustomerEntity customerEntity = customerRepository.findOne(dto.getCustomer_id());
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        TransactionEntity transactionEntity = customerConverter.convertToEntity(dto);
        transactionEntities.add(transactionEntity);
        customerEntity.setTransactionEntities(transactionEntities);
    }

}
