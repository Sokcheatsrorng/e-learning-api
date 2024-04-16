package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseCategoryRequest;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;
import co.istad.elearningapi.mapper.CourseMapper;
import jakarta.validation.Valid;
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
    public Page<CourseDetailsResponse> getAllCourses(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page <Course> coursePage = courseRepository.findAll(pageRequest);

        return coursePage.map(courseMapper::toCourseDetailResponse);
    }

    @Override
    public void createNewCourse(CourseCreateRequest request) {
        // check if user is instructor
        Instructor instructor = instructorRepository.findById(request.instructorId()).orElseThrow(()->
                 new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor has not been found!")
        );
        // if instructor is blocked
        if(instructor.isBlocked()){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                  "Instructor has been blocked!");
        }
        Instructor instructors = new Instructor();
        instructors.setWebsite(instructor.getWebsite());

        instructors.setGithub(instructor.getGithub());
        instructors.setBiography(instructor.getBiography());
        instructorRepository.save(instructors);

        Category category = new Category();
        category.setId(request.categoryId());

        Course course = new Course();
        course.setAlias(request.alias());
        course.setDescription(request.description());
        course.setTitle(request.title());
        course.setThumbnail(mediaBaseUri + "IMAGE/" +request.thumbnail());
        course.setDeleted(false);
        course.setCategory(category);
        course.setInstructor(instructor);
        courseRepository.save(course);

    }

}
