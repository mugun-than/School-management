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
        final Tutor tutor = new Tutor();
        tutor.setName(tutorDTO.getTutorName());
        tutor.setAddress(tutorDTO.getAddress());
        tutor.setSchool(schoolRepo.findById(tutorDTO.getSchoolId()).orElseThrow(NullPointerException::new));
        tutor.setCreatedAt(Instant.now());
        return this.tutorRepo.save(tutor);
    }

    public Tutor findById(Long tutorId) {
        return this.tutorRepo.findById(tutorId).orElseThrow(NullPointerException::new);
    }

    public List<Tutor> findBySchool(Long schoolId) {
        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        return this.tutorRepo.findBySchool(school);
    }

    public List<Tutor> findAll() {
        return this.tutorRepo.findAll();
    }

    public Tutor updateTutor(Long tutorId, TutorDTO tutorDTO) {
        final Tutor temp = this.tutorRepo.findById(tutorId).orElseThrow(NullPointerException::new);
        final School school = this.schoolRepo.findById(tutorDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        temp.setUpdatedAt(Instant.now());
        temp.setAddress(tutorDTO.getAddress());
        temp.setName(tutorDTO.getTutorName());
        temp.setSchool(school);
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
        final School school = schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        this.tutorRepo.deleteBySchool(school);
        return "Tutor of school: "+school.getName()+" deleted";
    }
}

