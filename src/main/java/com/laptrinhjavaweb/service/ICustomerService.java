package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchResquestDTO;
import com.laptrinhjavaweb.dto.request.TransactionDTOResquest;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponseDTO> find(CustomerSearchResquestDTO dto);
    void save(CustomerDTO dto);
    CustomerDTO findOneById(Long id);
    void delete(Long [] ids);
    void addTransaction(TransactionDTOResquest dto);
}
