package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    Instructor fromInstructorCreateRequest(InstructorCreateRequest instructorCreateRequest);

}
