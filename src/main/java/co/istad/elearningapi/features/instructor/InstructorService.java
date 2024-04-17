package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import org.springframework.data.domain.Page;

public interface InstructorService {
    void createNew(InstructorCreateRequest instructorCreateRequest);

    Page<InstructorResponse> findList(int page, int limit);

    InstructorResponse findProfileByUsername(String username);

    void updateProfile(String username, String mediaName);
}
