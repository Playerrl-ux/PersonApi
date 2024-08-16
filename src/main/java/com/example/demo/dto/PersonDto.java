package com.example.demo.dto;

import com.example.demo.entity.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PersonDto(

        @NotEmpty
        @Size(min = 2, max = 100)
        String firstName,
        @NotEmpty
        @Size(min = 2, max = 100)
        String lastName,
        String cpf,
        LocalDate birthDate,
        @Valid
        List<PhoneDto> phones
) {
}
