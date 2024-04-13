package co.istad.elearningapi.features.course;

import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;

public interface CourseService {

    CourseDetailsResponse findCourseDetailsByAlias(String alias);
    void updateCourseByAlias(String alias, CourseUpdateRequest request);

}
