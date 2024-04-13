package co.istad.elearningapi.features.course;


import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{alias}")
    CourseDetailsResponse findByAlias(@PathVariable String alias){
        return courseService.findCourseDetailsByAlias(alias);
    }

}
