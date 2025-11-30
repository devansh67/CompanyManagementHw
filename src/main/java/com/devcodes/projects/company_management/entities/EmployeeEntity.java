package com.devcodes.projects.company_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // @NotNull, @NotBlank, @Length, @Size examples
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Length(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    String name;

    // @Size example for description/bio
    @Size(min = 10, max = 500, message = "Bio must be between 10 and 500 characters")
    String bio;

    // @Pattern example for employee ID
    @Pattern(regexp = "^EMP-[0-9]{6}$", message = "Employee ID must be in format EMP-XXXXXX")
    String employeeId;

    // @Email example
    @Email(message = "Email must be a valid email address")
    String email;

    // @URL example for LinkedIn profile
    @URL(message = "LinkedIn profile must be a valid URL")
    String linkedInProfile;

    // @Min, @Max, @Positive, @PositiveOrZero examples
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot exceed 100")
    @Positive(message = "Age must be positive")
    Integer age;

    // @DecimalMin, @DecimalMax, @Positive, @PositiveOrZero examples
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be at least 0.0")
    @DecimalMax(value = "1000000.0", inclusive = true, message = "Salary cannot exceed 1,000,000.0")
    @PositiveOrZero(message = "Salary must be positive or zero")
    BigDecimal salary;

    // @Negative, @NegativeOrZero examples
    @Negative(message = "Deduction amount must be negative")
    BigDecimal monthlyDeduction;

    @NegativeOrZero(message = "Balance must be negative or zero")
    BigDecimal accountBalance;

    // @Digits example
    @Digits(integer = 8, fraction = 2, message = "Bonus must have at most 8 integer digits and 2 decimal places")
    BigDecimal bonus;

    // @Range example
    @Range(min = 1, max = 5, message = "Performance rating must be between 1 and 5")
    Integer performanceRating;

    // @Past, @PastOrPresent examples
    @Past(message = "Date of birth must be in the past")
    LocalDate dob;

    @PastOrPresent(message = "Date of hire must be in the past or present")
    LocalDate dateOfHire;

    @PastOrPresent(message = "Last login must be in the past or present")
    LocalDateTime lastLogin;

    // @Future, @FutureOrPresent examples
    @Future(message = "Contract end date must be in the future")
    LocalDate contractEndDate;

    @FutureOrPresent(message = "Next review date must be in the future or present")
    LocalDate nextReviewDate;

    // @NotEmpty example for collections
    @NotEmpty(message = "Skills list cannot be empty")
    List<String> skills;

    // @Null example - a field that should be null initially
    @Null(message = "Termination reason must be null for active employees")
    String terminationReason;

    // @CreditCardNumber example
    @CreditCardNumber(message = "Credit card number must be a valid credit card number")
    String creditCardNumber;

    // @NotNull, @AssertTrue, @AssertFalse examples
    @NotNull(message = "Permanent status cannot be null")
    Boolean isPermanent;

    @AssertTrue(message = "Employee must be verified before activation")
    Boolean isVerified;

    @AssertFalse(message = "Employee cannot be on leave if active")
    Boolean isOnLeave;

    // @Pattern example for phone number
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be a valid international format")
    String phoneNumber;

    List<String> addressList;
}
