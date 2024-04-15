package co.istad.elearningapi.features.course;


import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.base.BaseResponse;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseThumbnailRequest;
import co.istad.elearningapi.features.course.dto.CourseUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
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

    @PutMapping("{alias}/thumbnail")
    BaseResponse<?> updateCourseThumbnail(@PathVariable String alias,
                                          @RequestBody CourseThumbnailRequest request){
        String newThumbnail = courseService.updateCourseThumbnail(alias, request.thumbnail());

        return BaseResponse.builder()
                .payload(newThumbnail)
                .build();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewCourse(@RequestBody CourseCreateRequest request){
        courseService.createNewCourse(request);
    }

}
