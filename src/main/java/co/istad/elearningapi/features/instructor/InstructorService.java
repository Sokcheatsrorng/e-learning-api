package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.instructor.dto.InstructorUpdateRequest;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface InstructorService {
    void createNew(InstructorCreateRequest instructorCreateRequest);

    Page<InstructorResponse> findList(int page, int limit);

    UserDetailsResponse findProfileByUsername(String username);

    void updateProfile(String username, InstructorUpdateRequest instructorUpdateRequest);
}
