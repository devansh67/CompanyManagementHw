package com.devcodes.projects.company_management.services;

import com.devcodes.projects.company_management.dtos.DepartmentDTO;
import com.devcodes.projects.company_management.entities.DepartmentEntity;
import com.devcodes.projects.company_management.exceptions.ResourceNotFoundException;
import com.devcodes.projects.company_management.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    // This is used to create a new department in the database
    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity departmentEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        // Set ID to null to ensure it's treated as a new entity (persist) not an update (merge)
        departmentEntity.setId(null);
        // Set createdAt if not provided
        if (departmentEntity.getCreatedAt() == null) {
            departmentEntity.setCreatedAt(Instant.now());
        }
        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    // This is used to get the list of all departments from the database and do the transformation to DTO in stream data
    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    // Get department using departmentId
    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository
                .findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    // Change all the department details that are already present with a different set of details
    public DepartmentDTO updateDepartmentById(DepartmentDTO inputDepartment, Long id) {
        if (!isDepartmentPresentById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        DepartmentEntity departmentEntity  = modelMapper.map(inputDepartment, DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity newDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(newDepartment, DepartmentDTO.class);
    }

    // Delete a department by inputted id
    public void deleteDepartmentById(Long id) {
        if (!isDepartmentPresentById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    public DepartmentDTO partialUpdateDepartmentById(Map<String, Object> fieldToUpdate, Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        
        fieldToUpdate.forEach((field, value) -> {
            Field targetField = ReflectionUtils.findField(DepartmentEntity.class, field);
            if (targetField == null) {
                throw new IllegalArgumentException("Field '" + field + "' does not exist in DepartmentEntity");
            }
            targetField.setAccessible(true);
            ReflectionUtils.setField(targetField, departmentEntity, value);
        });
        
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    // Helper functions
    public boolean isDepartmentPresentById(Long id) {
        return departmentRepository.existsById(id);
    }
}
