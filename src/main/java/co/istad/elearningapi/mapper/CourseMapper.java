package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        InstructorMapper.class,
        CategoryMapper.class
})
public interface CourseMapper {

    @Mapping(target = "instructor", source = "course.instructor")
    @Mapping(target = "category", source = "course.category")
    CourseDetailsResponse toCourseDetailResponse(Course course);

    Course fromCourseCreateRequest(CourseCreateRequest courseCreateRequest);


    CourseDetailsResponse toCourseDetailsResponse(Course course);
}
