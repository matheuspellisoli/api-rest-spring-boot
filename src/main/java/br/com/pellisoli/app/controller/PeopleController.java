package br.com.pellisoli.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pellisoli.app.models.PersonModel;
import br.com.pellisoli.app.repository.PersonRepository;
import br.com.pellisoli.app.validation.PersonValidation;

@RestController
@RequestMapping(value = "/api/v1")
public class PeopleController {

	@Autowired
	PersonRepository personRepository;

	private PersonValidation personValidation;

	public PeopleController() {
		personValidation = new PersonValidation();

	}

	@GetMapping("/people")
	public ResponseEntity<List<PersonModel>> getAll() {
		return ResponseEntity.ok(personRepository.findAll());
	}

	@GetMapping("/people/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value = "id") long id) {
		PersonModel person = personRepository.findById(id);
		
		if(person == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(person);
	}

	@PostMapping("/people")
	public ResponseEntity<Object> post(@RequestBody PersonModel person) {

		if (!personValidation.IsValid(person)) {
			return new ResponseEntity<>(personValidation.getBrokenRules(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return ResponseEntity.ok(personRepository.save(person));
	}

	@PutMapping("/people")
	public ResponseEntity<Object> put(@RequestBody PersonModel person) {
		if (!personValidation.IsValid(person)) {
			return new ResponseEntity<>(personValidation.getBrokenRules(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return ResponseEntity.ok(personRepository.save(person));
	}

	@DeleteMapping("/people")
	public ResponseEntity<PersonModel> delete(@RequestBody PersonModel person) {
		personRepository.delete(person);
		return ResponseEntity.ok(null);
	}
}
