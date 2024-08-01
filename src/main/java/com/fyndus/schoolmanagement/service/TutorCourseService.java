package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.DTO.TutorCourseDTO;
import com.fyndus.schoolmanagement.entity.*;
import com.fyndus.schoolmanagement.exceptions.NoSuchElementFoundException;
import com.fyndus.schoolmanagement.exceptions.NullPointerException;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.SchoolCourseRepository;
import com.fyndus.schoolmanagement.repository.TutorCourseRepository;
import com.fyndus.schoolmanagement.repository.TutorRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.hibernate.query.derived.AnonymousTupleTableGroupProducer;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TutorCourseService {

    private final TutorCourseRepository tutorCourseRepo;
    private final TutorRepository tutorRepo;
    private final SchoolCourseRepository schoolCourseRepo;

    public TutorCourseService(TutorCourseRepository tutorCourseRepo, SchoolCourseRepository schoolCourseRepo, TutorRepository tutorRepo) {
        this.tutorRepo = tutorRepo;
        this.tutorCourseRepo = tutorCourseRepo;
        this.schoolCourseRepo = schoolCourseRepo;
    }

    public ResponseDTO createTutorCourse(TutorCourseDTO tutorCourseDTO) {
        final Tutor tutor = this.tutorRepo.findById(tutorCourseDTO.getTutorId()).orElseThrow(NullPointerException::new);
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(tutorCourseDTO.getSchoolCourseId()).orElseThrow(NullPointerException::new);

        final TutorCourse tutorCourse = new TutorCourse();
        tutorCourse.setTutor(tutor);
        tutorCourse.setSchoolCourse(schoolCourse);
        tutorCourse.setCreatedAt(Instant.now());
        return ResponseDTO.builder()
                .data(tutorCourse)
                .message(ResponseMessage.CREATED)
                .build();
    }

    public ResponseDTO findById(Long tutorCourseId) {
        final TutorCourse tutorCourse = this.tutorCourseRepo.findById(tutorCourseId).orElseThrow(NullPointerException::new);
        return ResponseDTO.builder()
                .data(tutorCourse)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findAll() {
        final List<TutorCourse> tutorCourses = this.tutorCourseRepo.findAll();
        if(tutorCourses.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        return ResponseDTO.builder()
                .data(tutorCourses)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO findByCourse(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<TutorCourse> tutorCourses = this.tutorCourseRepo.findAllBySchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);
        return ResponseDTO.builder()
                .data(tutorCourses)
                .message(ResponseMessage.FOUND)
                .build();
    }

    public ResponseDTO updateTutorCourse(Long tutorCourseId, TutorCourseDTO tutorCourseDTO) {
        final TutorCourse tutorCourse = this.tutorCourseRepo.findById(tutorCourseId).orElseThrow(NullPointerException::new);
        final Tutor tutor = this.tutorRepo.findById(tutorCourseDTO.getTutorId()).orElseThrow(NullPointerException::new);
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(tutorCourseDTO.getSchoolCourseId()).orElseThrow(NullPointerException::new);
        tutorCourse.setUpdatedAt(Instant.now());
        tutorCourse.setSchoolCourse(schoolCourse);
        tutorCourse.setTutor(tutor);
        return ResponseDTO.builder()
                .data(this.tutorCourseRepo.save(tutorCourse))
                .message(ResponseMessage.UPDATED)
                .build();
    }

    public ResponseDTO deleteAll() {
        final List<TutorCourse> tutorCourses = this.tutorCourseRepo.findAll();
        if(tutorCourses.isEmpty()) {
            throw new NoSuchElementFoundException();
        }
        this.tutorCourseRepo.deleteAll();
        return ResponseDTO.builder()
                .data(tutorCourses)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteById(Long tutorCourseId) {
        final TutorCourse tutorCourse = this.tutorCourseRepo.findById(tutorCourseId).orElseThrow(NullPointerException::new);
        this.tutorCourseRepo.deleteById(tutorCourseId);
        return ResponseDTO.builder()
                .data(tutorCourse)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteBySchoolCourse(Long schoolCourseId) {
        final SchoolCourse schoolCourse = this.schoolCourseRepo.findById(schoolCourseId).orElseThrow(NullPointerException::new);
        final List<TutorCourse> tutorCourses = this.tutorCourseRepo.findAllBySchoolCourse(schoolCourse).orElseThrow(NoSuchElementFoundException::new);
        this.tutorCourseRepo.deleteByAllSchoolCourse(schoolCourse);
        return ResponseDTO.builder()
                .data(tutorCourses)
                .message(ResponseMessage.DELETED)
                .build();
    }

    public ResponseDTO deleteByTutor(Long tutorId) {
        final Tutor tutor = this.tutorRepo.findById(tutorId).orElseThrow(NullPointerException::new);
        final TutorCourse tutorCourse = this.tutorCourseRepo.findById(tutorId).orElseThrow(NullPointerException::new);
        this.tutorCourseRepo.deleteByTutor(tutor);
        return ResponseDTO.builder()
                .data(tutorCourse)
                .message(ResponseMessage.DELETED)
                .build();
    }
}
