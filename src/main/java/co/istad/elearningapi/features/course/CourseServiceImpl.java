package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.category.CategoryRepository;
import co.istad.elearningapi.features.course.dto.*;
import co.istad.elearningapi.features.instructor.InstructorRepository;
import co.istad.elearningapi.mapper.CourseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final CategoryRepository categoryRepository;

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

        return courseMapper.toCourseDetailResponse(course);
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
        Category category = categoryRepository.findByAlias(request.alias())
                        .orElseThrow(
                                () -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Category has not been found"
                                )
                        );

        course.setCategory(category);

        courseRepository.save(course);

    }

    @Transactional
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
    public Page<CourseDetailsResponse> getAllCourses(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page <Course> coursePage = courseRepository.findAll(pageRequest);

        return coursePage.map(courseMapper::toCourseDetailResponse);
    }

    @Override
    public void createNewCourse(CourseCreateRequest request) {
        // Check if the instructor exists and is not blocked
        Instructor instructor = instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found"));

        if (instructor.isBlocked()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor is blocked");
        }

        // Retrieve the category from the database or create a new one if needed
        Category category = categoryRepository.findById(request.categoryId())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setId(request.categoryId());
                    // You might want to set other properties of the category here
                    return categoryRepository.save(newCategory);
                });

        // Create the course
        Course course = new Course();
        course.setAlias(request.alias());
        course.setDescription(request.description());
        course.setTitle(request.title());
        course.setThumbnail(mediaBaseUri + "IMAGE/" + request.thumbnail());
        course.setDeleted(false);
        course.setCategory(category);
        course.setInstructor(instructor);
        courseRepository.save(course);
    }


}