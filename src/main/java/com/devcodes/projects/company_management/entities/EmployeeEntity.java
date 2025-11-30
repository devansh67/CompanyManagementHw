package com.devcodes.projects.company_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue
    Long id;
    String name;
    Integer age;
    Double salary;
    LocalDate dateOfHire;
    LocalDate dob;
    Long phoneNumber;
    String creditCardNumber;
    Boolean isPermanent;
    List<String> addressList;
}
