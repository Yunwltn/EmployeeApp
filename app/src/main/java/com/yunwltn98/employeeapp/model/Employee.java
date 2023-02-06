package com.yunwltn98.employeeapp.model;

import java.io.Serializable;

public class Employee implements Serializable {

    public int id;
    public String name;
    public int salary;
    public int age;
//    public String profileImage;

    public Employee(String name, int salary, int age){
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
    public Employee(int id, String name, int salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
