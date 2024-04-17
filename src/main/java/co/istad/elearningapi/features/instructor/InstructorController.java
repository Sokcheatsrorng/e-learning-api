package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.instructor.dto.InstructorUpdateRequest;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
@Slf4j
public class InstructorController {
    private final InstructorService instructorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody InstructorCreateRequest instructorCreateRequest) {
        instructorService.createNew(instructorCreateRequest);
    }

    @GetMapping
    Page<InstructorResponse> findList(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "2") int limit){
        return instructorService.findList(page, limit);
    }

    @GetMapping("/{username}")
    UserDetailsResponse findProfileByUsername(@PathVariable String username){
        return instructorService.findProfileByUsername(username);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{username}")
    void updateProfile(@PathVariable String username,
                            @Valid @RequestBody InstructorUpdateRequest instructorUpdateRequest
    ) {
        instructorService.updateProfile(username, instructorUpdateRequest);
    }

}
