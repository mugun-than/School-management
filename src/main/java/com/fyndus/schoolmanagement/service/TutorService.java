package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.TutorDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepo;
    private final SchoolRepository schoolRepo;

    public TutorService(TutorRepository tutorRepo, SchoolRepository schoolRepo) {
        this.schoolRepo = schoolRepo;
        this.tutorRepo = tutorRepo;
    }

    public Tutor createTutor(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor();
        tutor.setName(tutorDTO.getTutorName());
        tutor.setAddress(tutorDTO.getAddress());
        tutor.setSchool(schoolRepo.findByName(tutorDTO.getSchoolName()));
        tutor.setCreatedAt(Instant.now());
        return this.tutorRepo.save(tutor);
    }

    public Tutor findById(Long tutorId) {
        return this.tutorRepo.findById(tutorId).orElse(null);
    }

    public List<Tutor> findBySchool(Long schoolId) {
        final School school = School.builder().id(schoolId).build();
        return this.tutorRepo.findBySchool(school);
    }

    public List<Tutor> findAll() {
        return this.tutorRepo.findAll();
    }

    public Tutor updateTutor(Long tutorId, Tutor tutor) {
        final Tutor temp = this.tutorRepo.findById(tutorId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setAddress(tutor.getAddress());
        temp.setName(tutor.getName());
        temp.setSchool(tutor.getSchool());
        return this.tutorRepo.save(temp);
    }

    public String deleteAll() {
        this.tutorRepo.deleteAll();
        return "All tutor deleted";
    }

    public String deleteById(Long tutorId) {
        this.tutorRepo.deleteById(tutorId);
        return "Tutor with id: "+tutorId+" deleted";
    }

    public String deleteBySchool(Long schoolId) {
        final School school = schoolRepo.findById(schoolId).orElse(null);
        this.tutorRepo.deleteBySchool(school);
        return "Tutor of school: "+school.getName()+" deleted";
    }
}

