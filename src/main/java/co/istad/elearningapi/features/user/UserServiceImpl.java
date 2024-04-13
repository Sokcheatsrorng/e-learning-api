package co.istad.elearningapi.features.user;


import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public Page<UserDetailsResponse> findAll(int page, int size, Sort sort, String username, String email, String nationalIdCard, String phoneNumber, String name, String gender, String role) {


        return null;

    }
}
