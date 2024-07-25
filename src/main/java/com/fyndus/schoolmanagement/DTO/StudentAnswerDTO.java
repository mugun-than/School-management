package com.fyndus.schoolmanagement.DTO;

import lombok.Data;
import java.util.Map;

@Data
public class StudentAnswerDTO {

    private Long studentId;
    private Long courseId;
    private Map<Long, Long> answers;
}
