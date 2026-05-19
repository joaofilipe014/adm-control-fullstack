package com.admControl.adm_control_api.department.entity;

import java.time.*;

import org.springframework.cglib.core.Local;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="departments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist(){
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

}