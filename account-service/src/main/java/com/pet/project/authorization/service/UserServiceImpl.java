package com.pet.project.authorization.service;

import static java.lang.String.format;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.pet.project.authorization.domain.entity.ERole;
import com.pet.project.authorization.domain.entity.Roles;
import com.pet.project.authorization.domain.entity.User;
import com.pet.project.authorization.domain.request.RegistrationRequest;
import com.pet.project.authorization.domain.response.UserInfoResponse;
import com.pet.project.authorization.exception.EntityNotFoundException;
import com.pet.project.authorization.repository.RoleRepository;
import com.pet.project.authorization.repository.UserRepository;
import com.pet.project.authorization.transformer.UserTransformer;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_ERROR = "User with id = %s didn't found.";
    private static final String ROLE_NOT_FOUND_ERROR = "Role with name = %s didn't found.";
    private final UserRepository userRepository;
    private final UserTransformer userTransformer;
    private final RoleRepository roleRepository;

    public UserServiceImpl(final UserRepository userRepository, final UserTransformer userTransformerImpl,
            final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformerImpl;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserInfoResponse getUserInfo(final Long id) {
        final Optional<User> user = userRepository.findById(id);
        return userTransformer.entityToResponse(
                user.orElseThrow(() -> new EntityNotFoundException(format(USER_NOT_FOUND_ERROR, id))));
    }

    @Override
    public void delete(final Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(format(USER_NOT_FOUND_ERROR, id));
        }
    }

    @Override
    public Long createUser(final RegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Error: email is already in use!");
        }
        final Set<Roles> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException(format(ROLE_NOT_FOUND_ERROR, ERole.ROLE_USER.name()))));
        final User user = userTransformer.userRequestToEntity(request, roles);
        return userRepository.save(user).getId();
    }

}
