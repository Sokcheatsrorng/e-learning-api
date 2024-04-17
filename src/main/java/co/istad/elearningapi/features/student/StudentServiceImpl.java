package co.istad.elearningapi.features.student;


import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import co.istad.elearningapi.features.user.RoleRepository;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserSnippetResponse;
import co.istad.elearningapi.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    @Override
    public StudentResponse createStudent(StudentCreateRequest studentCreateRequest) {
        log.info("Creating a new student");


        if (!userRepository.existsByUsername(studentCreateRequest.username())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not been found"
            );
        }
        // check user has or not
        User user = userRepository.findByUsername(studentCreateRequest.username())
                .orElseThrow(
                         () -> new ResponseStatusException(
                                 HttpStatus.NOT_FOUND,
                                 "User not found!"
                         )
                );

        List<Role> roles = new ArrayList<>();

        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role not found"
                        ));

        roles.add(studentRole);
        user.setRoles(roles);

        Student student = studentMapper.fromStudentCreateRequest(studentCreateRequest);
        student.setHighSchool(studentCreateRequest.highSchool());
        student.setUniversity(studentCreateRequest.university());
        student.setIsBlocked(false);
        student.setUser(user);
        student = studentRepository.save(student);

        return studentMapper.toStudentResponse(student);
    }

    @Override
    public Page<StudentResponse> findAllStudents(int page,int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);

        Page<Student> students = studentRepository.findAll(pageRequest);

        return students.map(student -> studentMapper.toStudentResponse(student));
    }

    @Override
    public UserSnippetResponse findStudentByUsername(String username) {
        User  user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student has not been found!"
                        )
                );

        return new UserSnippetResponse(user.getUsername(),user.getProfile());
    }
    @Override
    public void updateStudent(String username, StudentUpdateRequest studentUpdateRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                         () -> new ResponseStatusException(
                                 HttpStatus.NOT_FOUND,
                                 "Student has not been found!"
                         )
                );
        user.setProfile(studentUpdateRequest.mediaName());
        userRepository.save(user);
    }
}
