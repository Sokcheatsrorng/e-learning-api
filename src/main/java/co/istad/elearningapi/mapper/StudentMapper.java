package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" ,uses={
        UserMapper.class
})
public interface StudentMapper {

    Student fromStudentCreateRequest(StudentCreateRequest studentCreateRequest);
    List<StudentResponse> toStudentResponseList(List<Student> students);
    @Mapping(source = "user", target = "user")
    StudentResponse toStudentResponse(Student student);


}
