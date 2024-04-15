package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseCategoryRequest;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;
import co.istad.elearningapi.features.instructor.InstructorRepository;
import co.istad.elearningapi.mapper.CourseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


//@Service
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final InstructorRepository instructorRepository;

    @Value("${MEDIA_BASE_URI}")
    private String mediaBaseUri;


    @Override
    public CourseDetailsResponse findCourseDetailsByAlias(String alias) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Course has not been found!"
                        )
                );

        return courseMapper.toCourseDetailsResponse(course);
    }

    @Override
    public void updateCourseByAlias(String alias, CourseUpdateRequest request) {
        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Course has not been found!"
                        )
                );

        course.setTitle(request.title());
        course.setDescription(request.description());

        courseRepository.save(course);

    }

    @Override
    public String updateCourseThumbnail(String alias, String thumbnail) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Course has not been found"
                        )
                );

        course.setThumbnail(thumbnail);
        courseRepository.save(course);

        return mediaBaseUri + "IMAGE/" + thumbnail;
    }

    @Override
    public void updateCourseCategoriesByAlias(String alias, CourseCategoryRequest request) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Course has not been found"
                        )
                );
        Category category = new Category();
        category.setAlias(request.alias());

        course.setCategory(category);

        courseRepository.save(course);

    }

    @Override
    public BaseMessage disableCourseByAlias(String alias) {

        if (!courseRepository.existsByAlias(alias)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course has not been found"
            );
        }

        courseRepository.disableCourseByAlias(alias);

        return new BaseMessage("Course has been disabled");
    }

    @Override
    public void createNewCourse(CourseCreateRequest request) {
        // check if user is instructor
        Instructor instructor = instructorRepository.findById(request.instructorId()).orElseThrow(()->
                 new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor has not been found!")
        );
        if(instructor.isBlocked()){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                  "Instructor has been blocked!");
        }

        Course course = new Course();
        course.setAlias(request.alias());
        course.setDescription(request.description());
        course.setTitle(request.title());
        course.setThumbnail(request.thumbnail());
        course.setDeleted(false);
//        course.setCategory(request.categoryId());
        course.setInstructor(instructor);
        courseRepository.save(course);

        Instructor instructors = new Instructor();
        instructors.setWebsite(instructor.getWebsite());

        instructors.setGithub(instructors.getGithub());
        instructors.setBiography(instructors.getBiography());
        instructorRepository.save(instructors);

    }

}
