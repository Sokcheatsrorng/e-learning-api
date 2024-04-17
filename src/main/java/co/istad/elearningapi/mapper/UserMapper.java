package co.istad.elearningapi.mapper;


import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserCreateRequest;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetailsResponse toUserDetailsResponse(User user);
    List<UserDetailsResponse> toUserDetailsResponseList(List<User> users);
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

}
