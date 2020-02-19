package com.springwebflux.demo.repository;

import com.springwebflux.demo.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRespository extends ReactiveMongoRepository<Person, String> {

	Flux<Person>  findAll();

	Mono<Person>  findByName(String name);

	Mono<Person>  save(Person p);

	Mono<Void>   delete(Person p);

}
