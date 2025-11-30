package com.devcodes.projects.company_management.entities;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class EmployeeEntity {
    Integer id;
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
