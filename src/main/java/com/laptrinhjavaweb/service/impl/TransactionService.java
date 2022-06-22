package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.response.TransactionDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private CustomerConverter customerConverter;
    @Override
    public List<TransactionDTO> findByCustomerID(Long id) {
        List<TransactionEntity> entities = transactionRepository.findByCustomer_Id(id);
        return entities.stream().map(item ->transactionConverter.convertToDTO(item))
                .collect(Collectors.toList());

    }

    @Override
    public Map<String, String> getTransactionType() {
        Map<String,String> types = new HashMap<>();
        for (TransactionTypeEnum item : TransactionTypeEnum.values()) {
            types.put(item.toString(),item.getTransactionTypeValue());
        }
        return types;
    }
}
