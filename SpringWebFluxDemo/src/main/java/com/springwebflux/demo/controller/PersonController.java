package com.springwebflux.demo.controller;

import com.springwebflux.demo.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springwebflux.demo.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRespository personRespository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Person> createPerson(@RequestBody Person p) {
		return personRespository.save(p);
	}
	@GetMapping
	public Flux<Person> getAllPersons(){
		return personRespository.findAll();
	}
	@GetMapping(value = "/{name}")
	public Mono<ResponseEntity<Person>> index(@PathVariable(value = "name") String name) {
		return personRespository.findByName(name)
				.map(savedName ->ResponseEntity.ok(savedName))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	@PutMapping(value = "/{name}")
	public Mono<ResponseEntity<Person>> updatePerson(@PathVariable(value = "name") String name, @RequestBody Person person) {

		return personRespository.findByName(name)
				.flatMap(existingPerson -> {
					existingPerson.setName(person.getName());
					existingPerson.setAge(person.getAge());
					return personRespository.save(existingPerson);
				})
				.map(updatedPerson -> new ResponseEntity<>(updatedPerson, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	@DeleteMapping(value = "/{name}")
	public Mono<ResponseEntity<Void>> deletePerson(@PathVariable(value = "name") String name){
		return personRespository.findByName(name)
				.flatMap(existingPerson->personRespository.delete(existingPerson)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
