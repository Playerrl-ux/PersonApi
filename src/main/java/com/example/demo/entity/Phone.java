package com.example.demo.entity;

import com.example.demo.dto.PhoneDto;
import com.example.demo.enums.PhoneType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType phoneType;



    public Phone(PhoneDto phoneDto) {
        this.phoneType = phoneDto.phoneType();
        this.number = phoneDto.number();
    }

    public String toString(){
        return this.number + " " + this.phoneType;
    }
}
