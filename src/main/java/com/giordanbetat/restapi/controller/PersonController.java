package com.giordanbetat.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giordanbetat.restapi.model.Person;
import com.giordanbetat.restapi.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class PersonController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> save(@RequestBody Person person) {

		person.getTelephones().forEach(telephone -> telephone.setPerson(person));

		Person personSave = userRepository.save(person);

		return new ResponseEntity<Person>(personSave, HttpStatus.OK);

	}

	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> edit(@RequestBody Person person) {

		person.getTelephones().forEach(telephone -> telephone.setPerson(person));
		
		Person personEdit = userRepository.save(person);

		return new ResponseEntity<Person>(personEdit, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = MediaType.ALL_VALUE)
	public ResponseEntity<String> remove(@PathVariable(value = "id") Long id) {

		userRepository.deleteById(id);

		return new ResponseEntity<String>("Usuário removido com sucesso", HttpStatus.OK);

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> findById(@PathVariable(value = "id") Long id) {

		Optional<Person> person = userRepository.findById(id);

		return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> findAll() {

		List<Person> list = (List<Person>) userRepository.findAll();

		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);

	}

}
