package com.example.demo.service;

import com.example.demo.entity.Phone;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository){
        this.phoneRepository = phoneRepository;
    }

    public Phone searchPhone(String number) throws ResourceNotFoundException{

        Optional<Phone> busca = phoneRepository.findByNumber(number);
        busca.orElseThrow(() -> new ResourceNotFoundException("The phone with number: " + number + " doesn't exist"));
        return busca.get();
    }

    public void savePhone(Phone phone){
        phoneRepository.save(phone);
    }
}
