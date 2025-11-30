package com.devcodes.projects.company_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@Entity
public class DepartmentEntity {
    @Id
    @GeneratedValue
    Long id;
    String title;
    Boolean isActive;
    Instant createdAt;
}