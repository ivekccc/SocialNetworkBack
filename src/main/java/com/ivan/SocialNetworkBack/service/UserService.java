package com.ivan.SocialNetworkBack.service;

import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserDTO;

public interface UserService {
    User register(UserDTO userDto);
    String verify(User user);
}
