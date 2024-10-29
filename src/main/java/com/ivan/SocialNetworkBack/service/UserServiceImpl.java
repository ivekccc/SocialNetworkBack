package com.ivan.SocialNetworkBack.service;

import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserDTO;
import com.ivan.SocialNetworkBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authMenager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(10);

    @Override
    public User register(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public String verify(User user) {
        Authentication authentication=authMenager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else{
            return "fail";
        }
    }


}
