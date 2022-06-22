package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.request.TransactionDTOResquest;
import com.laptrinhjavaweb.dto.response.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO convertToDTO(TransactionEntity entity){
        String transationType = TransactionTypeEnum.valueOf(entity.getCode()).getTransactionTypeValue().toString();
        TransactionDTO result = modelMapper.map(entity,TransactionDTO.class);
        result.setCode(transationType);
        if(entity.getCreatedDate() != null){
            result.setCreatedDate(entity.getCreatedDate().toString());
        }else
            result.setCreatedDate("");
        return result;
    }

    public TransactionEntity convertToEntity(CustomerDTO dto){
        TransactionEntity result = modelMapper.map(dto,TransactionEntity.class);
        result.setNote(dto.getNoteTransaction());
        return result;
    }

}
