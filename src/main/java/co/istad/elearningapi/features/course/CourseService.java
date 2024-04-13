package co.istad.elearningapi.features.course;

import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;

public interface CourseService {

    CourseDetailsResponse findCourseDetailsByAlias(String alias);

}
