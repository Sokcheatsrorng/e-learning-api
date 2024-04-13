package co.istad.elearningapi.features.user;


import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    Page<UserDetailsResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "") String sort,
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String nationalIdCard,
            @RequestParam(required = false, defaultValue = "") String phoneNumber,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String gender,
            @RequestParam(required = false, defaultValue = "") String role
    ) {
        Sort.Direction direction = Sort.Direction.ASC;
        String[] sortParams = sort.split(":");
        if (sortParams.length == 2 && sortParams[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        // Construct Sort object based on the sort parameter
        Sort sortObject = Sort.by(direction, "id");
        return userService.findAll(
                page,
                size,
                sortObject,
                username,
                email,
                nationalIdCard,
                phoneNumber,
                name,
                gender,
                role);
    }

}
