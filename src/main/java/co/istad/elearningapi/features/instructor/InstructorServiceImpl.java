package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.user.RoleRepository;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import co.istad.elearningapi.mapper.InstructorMapper;
import co.istad.elearningapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final InstructorMapper instructorMapper;
    private final InstructorRepository instructorRepository;

    @Override
    public void createNew(InstructorCreateRequest instructorCreateRequest) {

        if (!userRepository.existsByUsername(instructorCreateRequest.username())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not been found"
            );
        }

        User user = userRepository.findByUsername(instructorCreateRequest.username())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found!"
                        )
                );

        List<Role> roles = new ArrayList<>();

        Role instructorRole = roleRepository.findByName("INSTRUCTOR")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role not found"
                        ));
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Role not found!"
                        )
                );

        roles.add(userRole);
        roles.add(instructorRole);
        user.setRoles(roles);

        Instructor instructor = instructorMapper.fromInstructorCreateRequest(instructorCreateRequest);
        instructor.setBlocked(false);
        instructor.setUser(user);

        instructorRepository.save(instructor);

    }

    @Override
    public Page<InstructorResponse> findList(int page, int limit) {
        return null;
    }

    @Override
    public InstructorResponse findProfileByUsername(String username) {
        return null;
    }

    @Override
    public void updateProfile(String username, String mediaName) {

    }
}
