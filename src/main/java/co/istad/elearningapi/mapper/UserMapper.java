package co.istad.elearningapi.mapper;


import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetailsResponse toUserDetailsResponse(User user);

}
