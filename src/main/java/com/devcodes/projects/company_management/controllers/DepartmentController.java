package com.devcodes.projects.company_management.controllers;

import com.devcodes.projects.company_management.dtos.DepartmentDTO;
import com.devcodes.projects.company_management.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+id));
    }

    // Create a new department
    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO departmentDTO = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    // Update all details of a department using a departmentId
    @PostMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody @Valid DepartmentDTO inputDepartment, @PathVariable(name = "departmentId") Long id) {
        DepartmentDTO departmentDTO = departmentService.updateDepartmentById(inputDepartment, id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.ACCEPTED);
    }

    // Delete a department
    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable(name = "departmentId") Long id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> partialUpdateDepartmentById(@RequestBody Map<String, Object> fieldToUpdate, @PathVariable(name = "departmentId") Long id) {
        DepartmentDTO departmentDTO = departmentService.partialUpdateDepartmentById(fieldToUpdate, id);
        return ResponseEntity.ok(departmentDTO);
    }
}
