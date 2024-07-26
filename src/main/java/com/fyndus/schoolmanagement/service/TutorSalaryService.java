package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.entity.TutorSalary;
import com.fyndus.schoolmanagement.repository.TutorSalaryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TutorSalaryService {

    private final TutorSalaryRepository tutorSalaryRepo;

    public TutorSalaryService(TutorSalaryRepository tutorSalaryRepo) {
        this.tutorSalaryRepo = tutorSalaryRepo;
    }

    public TutorSalary createTutorSalary(TutorSalary tutorSalary) {
        tutorSalary.setCreatedAt(Instant.now());
        return this.tutorSalaryRepo.save(tutorSalary);
    }

    public List<TutorSalary> findAll() {
        return this.tutorSalaryRepo.findAll();
    }

    public TutorSalary findById(Long tutorSalaryId) {
        return this.tutorSalaryRepo.findById(tutorSalaryId).orElse(null);
    }

    public List<TutorSalary> findByTutor(Long tutorId) {
        final Tutor tutor = Tutor.builder().id(tutorId).build();
        return this.tutorSalaryRepo.findByTutor(tutor);
    }

    public TutorSalary updateTutorSalary(Long tutorSalaryId, TutorSalary tutorSalary) {
        final TutorSalary temp = this.tutorSalaryRepo.findById(tutorSalaryId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setTutor(tutorSalary.getTutor());
        temp.setSalary(tutorSalary.getSalary());
        return this.tutorSalaryRepo.save(temp);
    }

    public String deleteAll() {
        this.tutorSalaryRepo.deleteAll();
        return "All tutorSalary deleted";
    }

    public String deleteByTutor(Long tutorId) {
        final Tutor tutor = Tutor.builder().id(tutorId).build();
        this.tutorSalaryRepo.deleteByTutor(tutor);
        return "All tutorSalary of tutor: "+tutor.getName()+" deleted";
    }

    public String deleteById(Long tutorSalaryId) {
        this.tutorSalaryRepo.deleteById(tutorSalaryId);
        return " Tutor: "+tutorSalaryId+" deleted";
    }
}
