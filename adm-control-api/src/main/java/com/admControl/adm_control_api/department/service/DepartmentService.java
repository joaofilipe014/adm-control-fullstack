package com.admControl.adm_control_api.department.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.admControl.adm_control_api.department.dto.DepartmentRequestDTO;
import com.admControl.adm_control_api.department.dto.DepartmentResponseDTO;
import com.admControl.adm_control_api.department.entity.Department;
import com.admControl.adm_control_api.department.repository.DepartmentRepository;
import com.admControl.adm_control_api.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentResponseDTO create(DepartmentRequestDTO request){
        if (departmentRepository.existsByName(request.name())){
            throw new BusinessException("Department already exists");
        }

        Department department = Department.builder()
                .name(request.name())
                .description(request.description())
                .build();

        Department savedDepartment = departmentRepository.save(department);

        return new DepartmentResponseDTO(
            savedDepartment.getId(),
            savedDepartment.getName(),
            savedDepartment.getDescription(),
            savedDepartment.getActive()
        );
    }

    public DepartmentResponseDTO update(
        Long id,
        DepartmentRequestDTO request
    ) {
        Department department = departmentRepository.findById(id)
            .orElseThrow(() -> 
                new BusinessException("Department not found")

            );

        department.setName(request.name());
        department.setDescription(request.description());

        Department updatedDepartment = departmentRepository.save(department);

        return new DepartmentResponseDTO(
            updatedDepartment.getId(),
            updatedDepartment.getName(),
            updatedDepartment.getDescription(),
            updatedDepartment.getActive()
        );
    }

    // Função que mostra todos os departamentos, na forma de uma lista
    public List<DepartmentResponseDTO> findAll(){

        return departmentRepository.findAll()
                /* Stream API é um modelo funcional para processar coleções 
                    vantagens 
                    reduzir mutabilidade;
                    evitar loops verbosos;
                    tornar transformação mais declarativa.*/
                .stream()
                // .map() significa -> transformar um objeto em outro -> Department → DepartmentResponseDTO
                .map(department -> new DepartmentResponseDTO(
                    department.getId(),
                    department.getName(),
                    department.getDescription(),
                    department.getActive()
                ))
                .toList();
    }

    public DepartmentResponseDTO findById(Long id){

        Department department = departmentRepository.findById(id)
                /* Significado de .orElseThrow()
                    “se existir, devolva”
                    “senão, lance exceção” 
                    evita department == null*/
                .orElseThrow(() ->
                        new BusinessException("Department not found")

                );

        return new DepartmentResponseDTO(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getActive()
        );
    }

    public void delete(Long id){

        Department department = departmentRepository.findById(id)
            .orElseThrow(() ->
                    new BusinessException("Department not found")
            );
            
        department.setActive(false);

        departmentRepository.save(department);
    }
 }
