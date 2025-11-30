package com.devcodes.projects.company_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    // @NotNull, @NotBlank, @Length, @Size examples
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @Length(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    String title;

    // @Size example for description
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    String description;

    // @Pattern example for department code
    @Pattern(regexp = "^DEPT-[A-Z0-9]{4}$", message = "Department code must be in format DEPT-XXXX")
    String departmentCode;

    // @Email example
    @Email(message = "Contact email must be a valid email address")
    String contactEmail;

    // @URL example
    @URL(message = "Website must be a valid URL")
    String website;

    // @NotNull, @AssertTrue, @AssertFalse examples
    @NotNull(message = "Active status cannot be null")
    Boolean isActive;

    @AssertTrue(message = "Department must be verified before activation")
    Boolean isVerified;

    @AssertFalse(message = "Department cannot be archived if active")
    Boolean isArchived;

    // @Min, @Max, @Positive, @PositiveOrZero examples
    @Min(value = 1, message = "Minimum employee count is 1")
    @Max(value = 1000, message = "Maximum employee count is 1000")
    @Positive(message = "Employee count must be positive")
    Integer employeeCount;

    // @DecimalMin, @DecimalMax, @Positive, @PositiveOrZero examples
    @DecimalMin(value = "0.0", inclusive = true, message = "Budget must be at least 0.0")
    @DecimalMax(value = "1000000.0", inclusive = true, message = "Budget cannot exceed 1,000,000.0")
    @PositiveOrZero(message = "Budget must be positive or zero")
    BigDecimal budget;

    // @Negative, @NegativeOrZero examples
    @Negative(message = "Loss amount must be negative")
    BigDecimal quarterlyLoss;

    @NegativeOrZero(message = "Balance adjustment must be negative or zero")
    BigDecimal balanceAdjustment;

    // @Digits example
    @Digits(integer = 6, fraction = 2, message = "Revenue must have at most 6 integer digits and 2 decimal places")
    BigDecimal revenue;

    // @Range example
    @Range(min = 1, max = 10, message = "Priority must be between 1 and 10")
    Integer priority;

    // @Past, @PastOrPresent examples
    @Past(message = "Established date must be in the past")
    LocalDate establishedDate;

    @PastOrPresent(message = "Last updated date must be in the past or present")
    LocalDateTime lastUpdated;

    // @Future, @FutureOrPresent examples
    @Future(message = "Planned closure date must be in the future")
    LocalDate plannedClosureDate;

    @FutureOrPresent(message = "Next review date must be in the future or present")
    LocalDate nextReviewDate;

    // @NotEmpty example for collections
    @NotEmpty(message = "Manager names list cannot be empty")
    List<String> managerNames;

    // @Null example - a field that should be null initially
    @Null(message = "Deletion reason must be null for active departments")
    String deletionReason;

    // @CreditCardNumber example
    @CreditCardNumber(message = "Payment card number must be a valid credit card number")
    String paymentCardNumber;

    Instant createdAt;
}