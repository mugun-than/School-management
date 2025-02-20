package com.fyndus.schoolmanagement.DTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;
    private String question;
    private String option1;
    private String option2;
}