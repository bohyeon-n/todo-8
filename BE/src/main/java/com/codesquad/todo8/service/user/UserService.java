package com.codesquad.todo8.service.user;

import com.codesquad.todo8.error.UserNotFoundException;
import com.codesquad.todo8.model.User;
import com.codesquad.todo8.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  public User getUserByName(String name) {
    return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException(name));
  }
}
