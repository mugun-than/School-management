package com.fyndus.schoolmanagement.DTO;

import lombok.Data;

@Data
public class QuestionEntryDTO {

    private String question;
    private String option1;
    private String option2;
    private Long ans;
    private Long schoolCourseId;
}
