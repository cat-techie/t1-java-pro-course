package ru.t1.pmorozov;

public class Employee {

    private final String name;
    private final int age;
    private final Position position;


    public Employee(String name, int age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
