package com.example.employeems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name = "Employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;
    private String empName;
    private String empAddress;
    private String empMNumber;
}
