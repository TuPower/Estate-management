package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchResquestDTO;
import com.laptrinhjavaweb.dto.request.TransactionDTOResquest;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerResponseDTO convertToDTO(CustomerEntity entity){
        CustomerResponseDTO dto = modelMapper.map(entity,CustomerResponseDTO.class);
        if (entity.getCreatedDate()!= null){
            dto.setCreatedDate(entity.getCreatedDate().toString());
        }else {
            dto.setCreatedDate(entity.getModifiedDate().toString());
        }
        if (entity.getCreatedBy() != null){
            dto.setCreatedBy(entity.getCreatedBy());
        }else {
            dto.setCreatedBy(entity.getModifiedBy());
        }
        return dto;
    }
    public CustomerDTO convertToDTOUpdate(CustomerEntity entity){
        CustomerDTO dto = modelMapper.map(entity,CustomerDTO.class);
        return dto;
    }
    public TransactionEntity convertToEntity(TransactionDTOResquest dto){
        TransactionEntity result = modelMapper.map(dto,TransactionEntity.class);
        return result;
    }
    public CustomerSearchResquestDTO convertToDTO(CustomerSearchResquestDTO dto){
        CustomerSearchResquestDTO result = modelMapper.map(dto,CustomerSearchResquestDTO.class);
        if(dto.getName() == null){
            result.setName("");
        }if(dto.getPhone() == null){
            result.setPhone("");
        }if(dto.getEmail() == null){
            result.setEmail("");
        }
        return result;
    }
    public CustomerEntity convertToEntity(CustomerDTO dto){
        CustomerEntity entity = modelMapper.map(dto,CustomerEntity.class);
        return entity;
    }
}
