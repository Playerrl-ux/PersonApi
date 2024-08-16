package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.PhoneDto;
import com.example.demo.entity.Person;
import com.example.demo.entity.Phone;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public PersonDto searchPerson(Long id) throws ResourceNotFoundException{
        Person busca = verifyIfExists(id);
        List<PhoneDto> phones = returnPhonesDto(busca);
        return PersonDto.builder().phones(phones).birthDate(busca.getBirthDate())
                .cpf(busca.getCpf()).lastName(busca.getLastName()).firstName(busca.getFirstName())
                .build();
    }

    public void savePerson(PersonDto personDto){
        List<Phone> phones = personDto.phones().stream().map(Phone::new).toList();
        System.out.println(phones);
        Person person = Person.builder().firstName(personDto.firstName())
                .lastName(personDto.lastName()).cpf(personDto.cpf())
                        .birthDate(personDto.birthDate()).phones(phones).build();
        personRepository.save(person);
    }

    public void deletePersonById(Long id) throws ResourceNotFoundException {
        Person busca = verifyIfExists(id);
        personRepository.delete(busca);
    }

    public Person verifyIfExists(Long id) throws ResourceNotFoundException {
        Optional<Person> busca = personRepository.findById(id);
        busca.orElseThrow(() -> new ResourceNotFoundException("The person with id: " + id + " doesn't exist"));
        return busca.get();
    }

    public void updateById(Long id, PersonDto personDto) throws ResourceNotFoundException {
        Person busca = verifyIfExists(id);
        List<Phone> phones = personDto.phones().stream().map(Phone::new).toList();
        System.out.println(phones);
        Person person = Person.builder().id(busca.getId()).firstName(personDto.firstName())
                .lastName(personDto.lastName()).cpf(personDto.cpf())
                .birthDate(personDto.birthDate()).phones(phones).build();
        personRepository.save(person);

    }

    public Page<PersonDto> paginatedSearchPerson(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Person> allModel = personRepository.findAll(pageable);
        List<PersonDto> allDto = allModel.stream().map(busca -> PersonDto.builder().phones(returnPhonesDto(busca)).birthDate(busca.getBirthDate())
                .cpf(busca.getCpf()).lastName(busca.getLastName()).firstName(busca.getFirstName())
                .build()).toList();
        return new PageImpl<>(allDto, pageable, allDto.size());

    }

    public List<PhoneDto> returnPhonesDto(Person person){
        return person.getPhones().stream().map(e -> PhoneDto.builder().
                phoneType(e.getPhoneType()).number(e.getNumber()).build()).toList();
    }
}
