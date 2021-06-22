package com.hjkportfolio.hjk;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Sample {

    @Test
    public void assertAllTest(){
        Assertions.assertAll(()->{
            System.out.println("first");
        }, ()->{
            System.out.println("second");
        }, ()->{
            System.out.println("third");
        });
    }


    @Test
    public void assertArrayEqualsTest() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};

        Assertions.assertArrayEquals(expected, actual);
    }

    public static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }
    }

    @Test
    public void assertEqualsTest() {
        Person expectedPerson = new Person("James", 20);
        Person actualPerson = new Person("James", 20);

        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void assertNotEqualsTest() {
        Person expectedPerson = new Person("James", 20);
        Person actualPerson = new Person("James", 30);

        Assertions.assertNotEquals(expectedPerson, actualPerson);
    }

    @Test
    public void assertNotNullTest() {
        Person person = new Person("James", 20);

        Assertions.assertNotNull(person);
    }


    @Test
    public void assertSameTest() {
        Person expectedPerson = new Person("James", 20);
        Person actualPerson = expectedPerson;

        Assertions.assertSame(expectedPerson, actualPerson);
    }

    @Test
    public void assertNotSameTest() {
        Person expectedPerson = new Person("James", 20);
        Person actualPerson = new Person("James", 20);

        Assertions.assertNotSame(expectedPerson, actualPerson);
    }

    @Test
    public void assertThrowsExceptionTest() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->{
            int[] arr = {1, 2, 3, 5};
            arr[5] = 0;});
    }

    @Test
    public void assertTimeOutTest() {
        Assertions.assertTimeout(Duration.ofMinutes(3), ()->{
            int sum = 0;

            for(int i = 0 ; i < 500000 ; i++){
                sum += i;
            }

            System.out.println(sum);
        });
    }

    @Test
    public void assertTrueTest(){
        Assertions.assertTrue(2==2);
    }

    @Test
    public void assertFalseTest(){
        Assertions.assertFalse(2==3);
    }

    @Test
    public void failTest(){
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(i);
            if(i == 3) {
                Assertions.fail("이 테스트는 실패합니다.");
            }
        }
    }

}

