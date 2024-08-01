package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.TutorDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.repository.TutorRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
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

    public ResponseDTO createTutor(TutorDTO tutorDTO) {
        final School school = this.schoolRepo.findById(tutorDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        final Tutor tutor = new Tutor();
        tutor.setName(tutorDTO.getTutorName());
        tutor.setAddress(tutorDTO.getAddress());
        tutor.setSchool(school);
        tutor.setCreatedAt(Instant.now());
        return ResponseDTO.builder()
                .data(this.tutorRepo.save(tutor))
                .message(ResponseMessage.CREATED)
                .build();
    }

    public ResponseDTO findById(Long tutorId) {
        final Tutor tutor = this.tutorRepo.findById(tutorId).orElseThrow(NullPointerException::new);
        return ResponseDTO.builder()
                .data(tutor)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findBySchool(Long schoolId) {
        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        final List<Tutor> tutors = this.tutorRepo.findAllBySchool(school).orElseThrow(NoSuchElementFoundException::new);
        return ResponseDTO.builder()
                .data(tutors)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findAll() {
        final List<Tutor> tutors = this.tutorRepo.findAll();
        if(tutors.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        return ResponseDTO.builder()
                .data(tutors)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO updateTutor(Long tutorId, TutorDTO tutorDTO) {
        final School school = this.schoolRepo.findById(tutorDTO.getSchoolId()).orElseThrow(NullPointerException::new);
        final Tutor tutor = this.tutorRepo.findById(tutorId).orElseThrow(NullPointerException::new);
        tutor.setUpdatedAt(Instant.now());
        tutor.setAddress(tutorDTO.getAddress());
        tutor.setName(tutorDTO.getTutorName());
        tutor.setSchool(school);
        return ResponseDTO.builder()
                .data(this.tutorRepo.save(tutor))
                .message(ResponseMessage.UPDATED)
                .build();
    }

    public ResponseDTO deleteAll() {
        final List<Tutor> tutors = this.tutorRepo.findAll();
        if(tutors.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        this.tutorRepo.deleteAll();
        return ResponseDTO.builder()
                .data(tutors)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteById(Long tutorId) {
        final Tutor tutor = this.tutorRepo.findById(tutorId).orElseThrow(NullPointerException::new);
        this.tutorRepo.deleteById(tutorId);
        return ResponseDTO.builder()
                .data(tutor)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteBySchool(Long schoolId) {
        final School school = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        final List<Tutor> tutors = this.tutorRepo.findAllBySchool(school).orElseThrow(NoSuchElementFoundException::new);
        this.tutorRepo.deleteAllBySchool(school);
        return ResponseDTO.builder()
                .data(tutors)
                .message(ResponseMessage.DELETED)
                .build();
    }
}

