package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/buscar/{id}")
    public PersonDto getPerson(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return personService.searchPerson(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto createPerson(@RequestBody @Valid PersonDto personDto){
        personService.savePerson(personDto);
        return new MessageDto("A Person has been created sucessfully");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDto deletePerson(@PathVariable("id") Long id) throws ResourceNotFoundException {
        personService.deletePersonById(id);
        return new MessageDto("Person deleted sucessfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDto updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) throws ResourceNotFoundException {
        personService.updateById(id, personDto);
        return new MessageDto("Person updated sucessfully");
    }

    @GetMapping("/list-all")
    public Page<PersonDto> listAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size)
    {
        return personService.paginatedSearchPerson(page, size);

    }
}
