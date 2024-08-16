package com.example.demo.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonDto personDto);

    PersonDto toDto(Person person);
}
