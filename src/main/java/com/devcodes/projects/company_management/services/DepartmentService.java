package com.devcodes.projects.company_management.services;

import com.devcodes.projects.company_management.dtos.DepartmentDTO;
import com.devcodes.projects.company_management.entities.DepartmentEntity;
import com.devcodes.projects.company_management.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
        DepartmentEntity departmentEntity  = modelMapper.map(inputDepartment, DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity newDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(newDepartment, DepartmentDTO.class);
    }

    // Delete a department by inputted id
    public Boolean deleteEmployeeById(Long id) {
        if(isDepartmentPresentById(id)) {
            departmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public DepartmentDTO partialUpdateDepartmentById(Map<String, Object> fieldToUpdate, Long id) {
        if(isDepartmentPresentById(id)) {
            DepartmentEntity departmentEntity = departmentRepository.findById(id).get();
            fieldToUpdate.forEach((field, value) -> {
                Field targetField = ReflectionUtils.findField(DepartmentEntity.class, field);
                assert targetField != null;
                targetField.setAccessible(true);
                ReflectionUtils.setField(targetField, departmentEntity, value);
            });
            return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
        } else {
            return  null;
        }
    }

    // Helper functions
    public boolean isDepartmentPresentById(Long id) {
        return departmentRepository.existsById(id);
    }
}
