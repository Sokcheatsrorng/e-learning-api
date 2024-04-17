package co.istad.elearningapi.features.user;


import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    List<UserDetailsResponse> findAll(
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
        if (sortParams.length == 2 && sortParams[1].equalsIgnoreCase("asc")) {
            direction = Sort.Direction.DESC;
        }

        // Construct Sort object based on the sort parameter
        Sort sortObject = Sort.by(direction, "id");
        return userService.findAll(
                sortObject,
                username,
                email,
                nationalIdCard,
                phoneNumber,
                name,
                gender,
                role);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    UserDetailsResponse findUserByUsername(@PathVariable String username) {
        return userService.findUserDetailsByUsername(username);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{username}/disable")
    BaseMessage disableUserByUsername(@PathVariable String username) {
        return userService.disableUserByUsername(username);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{username}/enable")
    BaseMessage enableUserByUsername(@PathVariable String username){
        return userService.enableUserByUsername(username);
    }
    @DeleteMapping("/{username}")
    void  deleteUserByUsername(@PathVariable String username){
        userService.deleteUerByUserName(username);
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserDetailsResponse createUser(@Valid @RequestBody UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest);
    }


}
