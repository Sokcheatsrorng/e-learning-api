package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.instructor.dto.InstructorUpdateRequest;
import co.istad.elearningapi.features.user.RoleRepository;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserResponse;
import co.istad.elearningapi.mapper.InstructorMapper;
import co.istad.elearningapi.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final InstructorMapper instructorMapper;
    private final InstructorRepository instructorRepository;
    private final UserMapper userMapper;

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
        PageRequest pageRequest = PageRequest.of(page, limit);

        Page<Instructor> instructors = instructorRepository.findAll(pageRequest);

        return instructors.map(instructorMapper::toInstructorResponse);
    }

    @Override
    public UserDetailsResponse findProfileByUsername(String username) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Username has not been found!"
                        ));

        return userMapper.toUserDetailsResponse(foundUser);
    }

    @Override
    public void updateProfile(String username, InstructorUpdateRequest instructorUpdateRequest) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> (
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Username has not been found!" )
                ));

        foundUser.setProfile(instructorUpdateRequest.mediaName());
        userRepository.save(foundUser);
    }
}
