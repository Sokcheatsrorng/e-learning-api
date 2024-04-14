package co.istad.elearningapi.init;

import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.user.AuthorityRepository;
import co.istad.elearningapi.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void initRoleAuthority() {
        if (roleRepository.count() < 1 && authorityRepository.count() < 1) {
            // Create authorities
            Authority userRead = new Authority();
            userRead.setName("user:read");
            Authority userWrite = new Authority();
            userWrite.setName("user:write");
            Authority userDelete = new Authority();
            userDelete.setName("user:delete");
            Authority userUpdate = new Authority();
            userUpdate.setName("user:update");
            Authority progressRead = new Authority();
            progressRead.setName("progress:read");
            Authority progressWrite = new Authority();
            progressWrite.setName("progress:write");
            Authority eLearningRead = new Authority();
            eLearningRead.setName("elearning:read");
            Authority eLearningWrite = new Authority();
            eLearningWrite.setName("elearning:write");
            Authority eLearningDelete = new Authority();
            eLearningDelete.setName("elearning:delete");
            Authority eLearningUpdate = new Authority();
            eLearningUpdate.setName("elearning:update");

            // Save authorities
            authorityRepository.saveAll(Arrays.asList(
                    userRead, userWrite, userDelete, userUpdate,
                    progressRead, progressWrite,
                    eLearningRead, eLearningWrite, eLearningDelete, eLearningUpdate
            ));

            // Create roles and associate authorities
            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setAuthorities(Arrays.asList(userRead, userWrite, userDelete, userUpdate));

            Role studentRole = new Role();
            studentRole.setName("STUDENT");
            studentRole.setAuthorities(Arrays.asList(progressRead, progressWrite));

            Role instructorRole = new Role();
            instructorRole.setName("INSTRUCTOR");
            instructorRole.setAuthorities(Arrays.asList(progressRead, progressWrite, eLearningRead, eLearningWrite));

            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setAuthorities(Arrays.asList(
                    userRead, userWrite, userDelete, userUpdate,
                    progressRead, progressWrite,
                    eLearningRead, eLearningWrite, eLearningDelete, eLearningUpdate
            ));

            // Save roles
            roleRepository.saveAll(Arrays.asList(userRole, studentRole, instructorRole, adminRole));
        }
    }
}
