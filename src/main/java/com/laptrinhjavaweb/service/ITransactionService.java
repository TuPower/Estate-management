package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.response.TransactionDTO;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
    List<TransactionDTO> findByCustomerID(Long id);
    Map<String, String> getTransactionType();
}
