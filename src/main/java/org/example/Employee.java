package org.example;

public class Employee {
    private String name;
    private int salary;
    private String department;

    public Employee(String name, int salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + ", department='" + department + "'}";
    }
}