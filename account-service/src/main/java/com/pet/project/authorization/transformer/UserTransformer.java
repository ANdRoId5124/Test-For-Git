package com.pet.project.authorization.transformer;

import com.pet.project.authorization.domain.entity.Roles;
import com.pet.project.authorization.domain.entity.User;
import com.pet.project.authorization.domain.request.RegistrationRequest;
import com.pet.project.authorization.domain.response.UserInfoResponse;

import java.util.Set;

public interface UserTransformer {

  User userRequestToEntity(RegistrationRequest request, Set<Roles> roles);

  UserInfoResponse entityToResponse(User user);
}
