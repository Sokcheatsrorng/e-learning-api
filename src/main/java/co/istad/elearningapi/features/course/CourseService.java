package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.course.dto.*;
import org.springframework.data.domain.Page;

public interface CourseService {

    CourseDetailsResponse findCourseDetailsByAlias(String alias);
    void updateCourseByAlias(String alias, CourseUpdateRequest request);

    String updateCourseThumbnail(String alias, String thumbnail);

    void createNewCourse (CourseCreateRequest request);

    void updateCourseCategoriesByAlias(String alias, CourseCategoryRequest request);

    BaseMessage disableCourseByAlias(String alias);

    Page<CourseDetailsResponse> getAllCourses(int page , int size);

}
