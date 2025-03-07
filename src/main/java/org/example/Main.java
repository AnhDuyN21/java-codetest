package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("David",50000, "Information Service"));
        employeeList.add(new Employee("Alice",60000, "Information Service"));
        employeeList.add(new Employee("John",2000, "Support"));
        employeeList.add(new Employee("Bob",4000, "Support"));
        employeeList.add(new Employee("Eva",70000, "Support"));
        employeeList.add(new Employee("Charlie",90000, "Support"));

        //Filter employees with a salary greater than 50,000.
        System.out.println("--------------Filter employees with a salary greater than 50,000-------------");
        for(Employee employee : FindEmployeesWithSalaryGreaterThan(employeeList,50000)){
            System.out.println(employee);
        }

        //Sort the employees by name in ascending order.
        System.out.println("--------------Sort the employees by name in ascending order-------------");
        for(Employee employee : SortEmployeesByNameAscending(employeeList)){
            System.out.println(employee);
        }

        //Transform the employee list to get only their names.
        System.out.println("--------------Transform the employee list to get only their names-------------");
        for(String employeeName : GetEmployeesName(employeeList)){
            System.out.println(employeeName);
        }

        //Find the average salary of all employees.
        System.out.println("--------------Find the average salary of all employees-------------");
        System.out.println(AverageSalaryOfEmployeeList(employeeList));

        //Group employees by department.
        System.out.println("--------------Group employees by department-------------");
        System.out.println("Information Service:");
        for(Employee employee : GroupEmployeesByDepartment(employeeList, "Information Service")){
            System.out.println(employee);
        }
        System.out.println("Support:");
        for(Employee employee : GroupEmployeesByDepartment(employeeList, "Support")){
            System.out.println(employee);
        }

        //Find the highest-paid employee.
        System.out.println("--------------Find the highest-paid employee-------------");
        for(Employee employee : FindHighestPaidEmployee(employeeList)){
            System.out.println(employee);
        }
    }
    public static List<Employee> FindEmployeesWithSalaryGreaterThan(List<Employee> employeeList, int salary){
        return employeeList.stream().filter(employee -> employee.getSalary() > salary)
                .toList();
    }
    public static List<Employee> SortEmployeesByNameAscending(List<Employee> employeeList){
        return employeeList.stream().sorted(Comparator.comparing(Employee::getName)).toList();
    }
    public static List<String> GetEmployeesName(List<Employee> employeeList){
        return employeeList.stream().map(Employee::getName).collect(Collectors.toList());
    }
    public static double AverageSalaryOfEmployeeList(List<Employee> employeeList){
        return employeeList.stream().mapToInt(Employee::getSalary).average().orElse(0);
    }
    public static List<Employee> GroupEmployeesByDepartment(List<Employee> employeeList, String department){
        return employeeList.stream()
                        .filter(employee -> employee.getDepartment()
                        .equals(department)).toList();
    }
    public static List<Employee> FindHighestPaidEmployee(List<Employee> employeeList){
        int maxSalary = employeeList.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
        return employeeList.stream()
                .filter(emp -> emp.getSalary() == maxSalary)
                .collect(Collectors.toList());
    }
}