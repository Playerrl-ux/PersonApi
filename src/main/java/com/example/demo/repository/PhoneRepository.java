package com.example.demo.repository;

import com.example.demo.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findByNumber(String number);
}
