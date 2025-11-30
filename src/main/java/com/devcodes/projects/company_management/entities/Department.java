package com.devcodes.projects.company_management.entities;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Department {
    Integer id;
    String title;
    Boolean isActive;
    Instant createdAt;
}
