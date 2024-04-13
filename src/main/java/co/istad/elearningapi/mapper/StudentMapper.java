package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import lombok.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponse toStudentResponse(Student student);


}
