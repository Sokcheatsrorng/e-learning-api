package co.istad.elearningapi.features.user;


import co.istad.elearningapi.features.user.dto.RoleResponse;
import co.istad.elearningapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    List<RoleResponse> findAllRoles(){
        return userService.findAllRoles();
    }
    @GetMapping("/{name}")
    RoleResponse findRoleByName(@PathVariable String name){
        return userService.findRoleByName(name);
    }
}
