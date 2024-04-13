package co.istad.elearningapi.features.user;

import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    Page<UserDetailsResponse> findAll(int page, int size,
                                      Sort sort,
                                      String username,
                                      String email,
                                      String nationalIdCard,
                                      String phoneNumber,
                                      String name,
                                      String gender,
                                      String role);
}
