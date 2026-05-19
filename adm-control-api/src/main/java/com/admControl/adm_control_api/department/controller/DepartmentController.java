package com.admControl.adm_control_api.department.controller;

import com.admControl.adm_control_api.AdmControlApiApplication;
import com.admControl.adm_control_api.department.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admControl.adm_control_api.department.dto.DepartmentRequestDTO;
import com.admControl.adm_control_api.department.dto.DepartmentResponseDTO;
import com.admControl.adm_control_api.department.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    
    private final DepartmentService departmentService;

    // Criar recurso
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> create(
            @Valid @RequestBody DepartmentRequestDTO request
    ) {
        
        DepartmentResponseDTO response = departmentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Buscar recurso
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        
        return ResponseEntity.ok(
            departmentService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> findById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(departmentService.findById(id));
    }
    
    // substituir/atualizar recurso
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> update(
        @PathVariable Long id,
        @Valid @RequestBody DepartmentRequestDTO reuqest
    ) {
        
        return ResponseEntity.ok(
            departmentService.update(id, reuqest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        departmentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}