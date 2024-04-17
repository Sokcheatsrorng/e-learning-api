package co.istad.elearningapi.features.user;


import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.mapper.RoleMapper;
import co.istad.elearningapi.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public List<UserDetailsResponse> findAll(Sort sort, String username, String email, String nationalIdCard, String phoneNumber, String name, String gender, String role) {

        Specification<User> spec = Specification.where(null);
        if (username != null && !username.isEmpty()){
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("username"), username));
        }
        if (email != null && !email.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("email"), email)));
        }
        if (nationalIdCard != null && !nationalIdCard.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("nationalIdCard"), nationalIdCard)));
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber)));
        }
        if (name != null && !name.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), name)));
        }
        if (gender != null && !gender.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("gender"), gender)));
        }
        if (role != null && !role.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("role"), role)));
        }

        List<User> users = userRepository.findAll(spec, sort);

        return userMapper.toUserDetailsResponseList(users);

    }

    @Override
    public UserDetailsResponse findUserDetailsByUsername(String username) {

        if (!userRepository.existsByUsername(username)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not been found"
            );
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found!"
                        )
                );

        return userMapper.toUserDetailsResponse(user);
    }

    @Transactional
    @Override
    public BaseMessage disableUserByUsername(String username) {

        if (!userRepository.existsByUsername(username)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not been found!"
            );
        }
        userRepository.disableByUsername(username);

        return new BaseMessage("User has been disabled");
    }

    @Transactional
    @Override
    public BaseMessage enableUserByUsername(String username) {

        if (!userRepository.existsByUsername(username)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not been found!"
            );
        }
        userRepository.enableUserByUsername(username);

        return new BaseMessage("User has been disabled");
    }

    @Override
    public void deleteUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not been found!"
            );
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found!"
                        )
                );
        userRepository.delete(user);

    }

    @Override
    public RoleResponse findRoleByName(String name) {
        if (!roleRepository.existsByName(name)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Role has not been found"
            );
        }
        Role role = roleRepository.findByName(name)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Role has not been found!"
                        )
                );
        return roleMapper.toRoleResponse(role);
    }
}
