package com.devcodes.projects.company_management.controllers;

import com.devcodes.projects.company_management.dtos.DepartmentDTO;
import com.devcodes.projects.company_management.services.DepartmentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // GET call to get the list of all departments in the database
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // Get department by id if exists in the database
    @GetMapping(path = "/{departmentId}")
    public Optional<ResponseEntity<DepartmentDTO>> getDepartmentById(@PathVariable(name = "departmentId") Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return Optional.of(departmentDTO
                .map(ResponseEntity::ok)
                .orElseThrow(NoSuchElementException::new));
    }

    // Create a new department
    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO departmentDTO = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    // Delete a department
    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable(name = "departmentId") Long id) {
        if(departmentService.deleteEmployeeById(id)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> partialUpdateDepartmentById(@RequestBody Map<String, Object> fieldToUpdate, @PathVariable(name = "departmentId") Long id) {
        DepartmentDTO departmentDTO = departmentService.partialUpdateDepartmentById(fieldToUpdate, id);
        if(departmentDTO != null) {
            return ResponseEntity.ok(departmentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
