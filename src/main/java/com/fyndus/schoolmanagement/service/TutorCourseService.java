package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.Course;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.entity.Tutor;
import com.fyndus.schoolmanagement.entity.TutorCourse;
import com.fyndus.schoolmanagement.repository.TutorCourseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TutorCourseService {

    private final TutorCourseRepository tutorCourseRepo;

    public TutorCourseService(TutorCourseRepository tutorCourseRepo) {
        this.tutorCourseRepo = tutorCourseRepo;
    }

    public TutorCourse createTutorCourse(TutorCourse tutorCourse) {
        tutorCourse.setCreatedAt(Instant.now());
        return this.tutorCourseRepo.save(tutorCourse);
    }

    public TutorCourse findById(Long tutorCourseId) {
        return this.tutorCourseRepo.findById(tutorCourseId).orElse(null);
    }

    public List<TutorCourse> findByCourse(Course course) {
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

    public String deleteByCourse(Course course) {
        this.tutorCourseRepo.deleteByCourse(course);
        return "All tutorCourse with course "+course.getName()+" daleted";
    }

    public String deleteByTutor(Tutor tutor) {
        this.tutorCourseRepo.deleteByTutor(tutor);
        return "All tutorCourse with tutor: "+tutor.getName()+" deleted";
    }
}
