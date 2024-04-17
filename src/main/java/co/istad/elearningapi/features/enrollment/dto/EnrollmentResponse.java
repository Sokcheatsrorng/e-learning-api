package co.istad.elearningapi.features.enrollment.dto;


import java.time.LocalTime;

public record EnrollmentResponse(

        Long id,
        Long studentId,
        LocalTime enrolledAt,
        boolean isCertified,
        Integer progress
){

}
