package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepo;

    public SchoolService(SchoolRepository schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    public School createSchool(School school) {
        school.setCreatedAt(Instant.now());
        return this.schoolRepo.save(school);
    }

    public School findById(Long schoolId) {
        return this.schoolRepo.findById(schoolId).orElse(null);
    }

    public List<School> findAll() {
        return this.schoolRepo.findAll();
    }

    public School updateSchool(Long schoolId, School school) {
        final School temp = this.schoolRepo.findById(schoolId).orElse(null);
        if(temp == null) return temp;
        temp.setName(school.getName());
        temp.setAddress(school.getAddress());
        temp.setUpdatedAt(Instant.now());
        return this.schoolRepo.save(temp);
    }
    
    public String deleteAll() {
        this.schoolRepo.deleteAll();
        return "All schools deleted";
    }

    public String deleteById(Long schoolId) {
        this.schoolRepo.deleteById(schoolId);
        return "School with id: "+schoolId+" deleted";
    }
}