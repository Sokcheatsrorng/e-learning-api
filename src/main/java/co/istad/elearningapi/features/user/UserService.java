package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.user.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    List<UserDetailsResponse> findAll(Sort sort,
                                      String username,
                                      String email,
                                      String nationalIdCard,
                                      String phoneNumber,
                                      String name,
                                      String gender,
                                      String role);

    UserDetailsResponse findUserDetailsByUsername(String username);

    BaseMessage disableUserByUsername(String username);

    BaseMessage enableUserByUsername(String username);
    // deleted user
    void deleteUerByUserName(String username);
    List<RoleResponse> findAllRoles();
    RoleResponse findRoleByName(String name);

    UserDetailsResponse createUser(UserCreateRequest userCreateRequest);
}
