package com.devcodes.projects.company_management.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    Long id;
    String title;
    Boolean isActive;
    Instant createdAt;
}
