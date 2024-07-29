package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.TutorCourseDTO;
import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.entity.TutorCourse;
import com.fyndus.schoolmanagement.repository.CourseRepository;
import com.fyndus.schoolmanagement.repository.TutorCourseRepository;
import com.fyndus.schoolmanagement.repository.TutorRepository;
import org.hibernate.query.derived.AnonymousTupleTableGroupProducer;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TutorCourseService {

    private final TutorCourseRepository tutorCourseRepo;
    private final TutorRepository tutorRepo;
    private final CourseRepository courseRepo;

    public TutorCourseService(TutorCourseRepository tutorCourseRepo, CourseRepository courseRepo, TutorRepository tutorRepo) {
        this.tutorRepo = tutorRepo;
        this.courseRepo = courseRepo;
        this.tutorCourseRepo = tutorCourseRepo;
    }

    public TutorCourse createTutorCourse(TutorCourseDTO tutorCourseDTO) {
        final Tutor tutor = this.tutorRepo.findById(tutorCourseDTO.getTutorId()).orElse(null);
        final Course course = this.courseRepo.findById(tutorCourseDTO.getCourseId()).orElse(null);

        TutorCourse tutorCourse = new TutorCourse();
        tutorCourse.setTutor(tutor);
        tutorCourse.setCourse(course);
        tutorCourse.setCreatedAt(Instant.now());
        return this.tutorCourseRepo.save(tutorCourse);
    }

    public TutorCourse findById(Long tutorCourseId) {
        return this.tutorCourseRepo.findById(tutorCourseId).orElse(null);
    }

    public List<TutorCourse> findAll() {
        return this.tutorCourseRepo.findAll();
    }

    public List<TutorCourse> findByCourse(Long courseId) {
        final Course course = Course.builder().id(courseId).build();
        return this.tutorCourseRepo.findByCourse(course);
    }

    public TutorCourse updateTutorCourse(Long tutorCourseId, TutorCourse tutorCourse) {
        final TutorCourse temp = this.tutorCourseRepo.findById(tutorCourseId).orElse(null);
        if(temp == null) return temp;
        temp.setUpdatedAt(Instant.now());
        temp.setCourse(tutorCourse.getCourse());
        temp.setTutor(tutorCourse.getTutor());
        return this.tutorCourseRepo.save(temp);
    }

    public String deleteAll() {
        this.tutorCourseRepo.deleteAll();
        return "All tutorCourse deleted";
    }

    public String deleteById(Long tutorCourseId) {
        this.tutorCourseRepo.deleteById(tutorCourseId);
        return "TutorCourse with id: "+tutorCourseId+" deleted";
    }

    public String deleteByCourse(Long courseId) {
        final Course course = this.courseRepo.findById(courseId).orElse(null);
        this.tutorCourseRepo.deleteByCourse(course);
        return "All tutorCourse with course "+course.getName()+" daleted";
    }

    public String deleteByTutor(Long tutorId) {
        final Tutor tutor = this.tutorRepo.findById(tutorId).orElse(null);
        this.tutorCourseRepo.deleteByTutor(tutor);
        return "All tutorCourse with tutor: "+tutor.getName()+" deleted";
    }
}
