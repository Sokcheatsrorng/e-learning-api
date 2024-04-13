package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    InstructorResponse toInstructorResponse(Instructor instructor);

}
