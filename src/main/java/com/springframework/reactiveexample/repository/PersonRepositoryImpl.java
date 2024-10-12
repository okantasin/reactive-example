package com.springframework.reactiveexample.repository;

import com.springframework.reactiveexample.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person okan = new Person(1,"Okan","t");
    Person ozan = new Person(2,"Ozan","t");
    Person mmd = new Person(3,"m","d");


    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(okan);
    }

    @Override
    public Flux<Person> getAll() {
        return Flux.just(okan,ozan,mmd);
    }
}
