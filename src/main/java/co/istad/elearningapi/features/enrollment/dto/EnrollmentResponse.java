package co.istad.elearningapi.features.enrollment.dto;


import co.istad.elearningapi.features.student.dto.StudentResponse;

import java.time.LocalTime;

public record EnrollmentResponse(

        String code,
        StudentResponse student,
        LocalTime enrolledAt,
        boolean isCertified,
        Integer progress
){

}
