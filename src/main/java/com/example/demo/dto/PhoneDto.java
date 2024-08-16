package com.example.demo.dto;

import com.example.demo.enums.PhoneType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;



@Builder
public record PhoneDto(

        @Enumerated(EnumType.STRING)
        PhoneType phoneType,

        @NotEmpty
        @Size(min = 14, max=15)
        String number
){}
