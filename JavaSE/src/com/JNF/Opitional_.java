package com.JNF;

import java.util.Optional;

import org.junit.Test;

public class Opitional_ {
    @Test
    public void test() {
        Person person = new Person();
        Optional<Person> personNull = Optional.ofNullable(person);
        System.out.println(personNull);

        person = null;
        Optional<Person> personNull1 = Optional.ofNullable(person);
        System.out.println(personNull1);
        Person orElse = Optional.ofNullable(person).orElse(new Person("123", 123));
        System.out.println(orElse);

    }
}
