package com.springframework.reactiveexample.repository;

import com.springframework.reactiveexample.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void getById() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();
        System.out.println(person.toString());
    }

    @Test
    void getByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(System.out::println);
    }

    @Test
    void getByIdMapFunction() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.map(person->{
            System.out.println(person.toString());
            return person.getFirstName();
        }).subscribe(System.out::println);

    }

    @Test
    void fluxTestBlockFirst() {
        Flux<Person> personFlux = personRepository.getAll();
        Person person = personFlux.blockFirst();
        System.out.println(person.toString());

    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.getAll();
        personFlux.subscribe(System.out::println);
    }

    @Test
    void testFluxToListMono() {
        Flux<Person> personFlux = personRepository.getAll();
        Mono<List<Person>> personList = personFlux.collectList();
        personList.subscribe(System.out::println);
    }

    @Test
    void testFindPersonByID() {
        Flux<Person> personFlux = personRepository.getAll();
        final Integer id = 2;
        Mono<Person> personMono = personFlux.filter(person -> person.getId() == id).next();
        personMono.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void getAll() {
    }
}