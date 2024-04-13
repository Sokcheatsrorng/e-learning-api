package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;
import co.istad.elearningapi.mapper.CourseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


//@Service
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

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
}
