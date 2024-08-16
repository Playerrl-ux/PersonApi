package com.example.demo.mapper;

import com.example.demo.dto.PhoneDto;
import com.example.demo.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    Phone toModel(PhoneDto phoneDto);

    PhoneDto toDto(Phone phone);
}
