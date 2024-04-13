package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

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
}