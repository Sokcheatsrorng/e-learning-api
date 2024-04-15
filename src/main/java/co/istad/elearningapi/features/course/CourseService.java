package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.course.dto.CourseCategoryRequest;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;

public interface CourseService {

    CourseDetailsResponse findCourseDetailsByAlias(String alias);
    void updateCourseByAlias(String alias, CourseUpdateRequest request);

    String updateCourseThumbnail(String alias, String thumbnail);

    void createNewCourse (CourseCreateRequest request);

    void updateCourseCategoriesByAlias(String alias, CourseCategoryRequest request);

    BaseMessage disableCourseByAlias(String alias);
}
