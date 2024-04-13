package co.istad.elearningapi.features.course;


import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{alias}")
    CourseDetailsResponse findByAlias(@PathVariable String alias){
        return courseService.findCourseDetailsByAlias(alias);
    }

    @PutMapping("/{alias}")
    void updateByAlias(@PathVariable String alias,
                       @RequestBody CourseUpdateRequest request){
        courseService.updateCourseByAlias(alias, request);
    }

}
