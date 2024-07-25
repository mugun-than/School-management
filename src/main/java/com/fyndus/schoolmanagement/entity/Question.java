package com.fyndus.schoolmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name="question")
@Data
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private Long ans;
    @ManyToOne
    private Course course;
    private Instant createdAt;
    private Instant updatedAt;
}