package com.pet.project.authorization.service;

import com.pet.project.authorization.domain.request.RegistrationRequest;
import com.pet.project.authorization.domain.response.UserInfoResponse;

public interface UserService {

  UserInfoResponse getUserInfo(Long id);

  void delete(Long id);

  Long createUser(RegistrationRequest request);

}
