package com.fyndus.schoolmanagement.service;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.entity.School;
import com.fyndus.schoolmanagement.repository.SchoolRepository;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepo;

    public SchoolService(SchoolRepository schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    public ResponseDTO createSchool(School school) {
        school.setCreatedAt(Instant.now());
        return ResponseDTO.builder().data(this.schoolRepo.save(school)).message(ResponseMessage.CREATED).build();
    }

    public ResponseDTO findById(Long schoolId) {
        try {
            return ResponseDTO.builder().data(this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new)).message(ResponseMessage.FOUND).build();
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
    }

    public ResponseDTO findAll() {
        final List<School> schools = this.schoolRepo.findAll();
        if(schools.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        return ResponseDTO.builder().data(schools).message(ResponseMessage.FOUND).build();
    }

    public ResponseDTO updateSchool(Long schoolId, School school) {
        final School temp;
        try {
            temp = this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        temp.setName(school.getName());
        temp.setAddress(school.getAddress());
        temp.setUpdatedAt(Instant.now());
        return ResponseDTO.builder().data(this.schoolRepo.save(temp)).message(ResponseMessage.UPDATED).build();
    }
    
    public ResponseDTO deleteAll() {
        final List<School> schools = this.schoolRepo.findAll();
        if(schools.isEmpty()) {
            return ResponseDTO.builder().message(ResponseMessage.EMPTY).build();
        }
        final ResponseDTO responseDTO = ResponseDTO.builder().data(schools).message(ResponseMessage.DELETED).build();
        this.schoolRepo.deleteAll();
        return responseDTO;
    }

    public ResponseDTO deleteById(Long schoolId) {
        final ResponseDTO responseDTO;
        try {
            responseDTO = ResponseDTO.builder().data(this.schoolRepo.findById(schoolId).orElseThrow(NullPointerException::new)).message(ResponseMessage.DELETED).build();
        } catch (NullPointerException e) {
            return ResponseDTO.builder().message(ResponseMessage.NOT_FOUND).build();
        }
        this.schoolRepo.deleteById(schoolId);
        return responseDTO;
    }
}