package org.example;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private int id;
    private String name;
    private String email;
    private double salary;
    private String department;
}
