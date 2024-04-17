package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
@Slf4j
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping
    void createNew(@Valid @RequestBody InstructorCreateRequest instructorCreateRequest, UserCreateRequest userCreateRequest) {
        instructorService.createNew(userCreateRequest, instructorCreateRequest);
    }
}
