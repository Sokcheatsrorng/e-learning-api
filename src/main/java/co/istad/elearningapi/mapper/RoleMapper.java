package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.user.dto.RoleAuthorityResponse;
import co.istad.elearningapi.features.user.dto.RoleResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse(Role role);

    RoleAuthorityResponse toRoleAuthorityResponse(String authorityName);

    List<RoleResponse> toRoleResponseList(List<Role> roles);


}
