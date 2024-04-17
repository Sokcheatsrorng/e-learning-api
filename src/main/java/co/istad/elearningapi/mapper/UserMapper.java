package co.istad.elearningapi.mapper;


import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetailsResponse toUserDetailsResponse(User user);
    List<UserDetailsResponse> toUserDetailsResponseList(List<User> users);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "instructors", ignore = true)
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "city", ignore = true)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

}
