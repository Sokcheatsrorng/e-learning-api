package co.istad.elearningapi.mapper;


import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetailsResponse toUserDetailsResponse(User user);
    List<UserDetailsResponse> toUserDetailsResponseList(List<User> users);

    @Named("mapUserResponse")
    default UserDetailsResponse mapUserResponse(List<User> userRoleList){
        return toUserDetailsResponse(userRoleList.get(0));
    }

}
