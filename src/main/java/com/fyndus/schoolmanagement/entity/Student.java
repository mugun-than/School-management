package com.fyndus.schoolmanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "student")
@Data
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    private School school;
    private Instant createdAt;
    private Instant updatedAt;
}