package co.istad.elearningapi.features.user;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.user.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    UserDetailsResponse findUserDetailsByUsername(String username);

    BaseMessage disableUserByUsername(String username);

    BaseMessage enableUserByUsername(String username);

    void deleteUserByUsername(String username);

    List<UserDetailsResponse> findAll(Sort sortObject, String username, String email, String nationalIdCard, String phoneNumber, String name, String gender, String role);

    RoleResponse findRoleByName(String name);

    
}
