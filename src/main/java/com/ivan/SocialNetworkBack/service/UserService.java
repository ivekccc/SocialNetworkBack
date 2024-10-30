package com.ivan.SocialNetworkBack.service;

import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserDTO;
import com.ivan.SocialNetworkBack.model.user.UserResponseDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User register(UserDTO userDto) throws IOException;
    String verify(User user);
    User findByUsername(String username);
    List<UserResponseDTO> searchUsers(String query);
}
