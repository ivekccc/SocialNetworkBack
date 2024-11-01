package com.ivan.SocialNetworkBack.service;

import com.ivan.SocialNetworkBack.model.user.User;
import com.ivan.SocialNetworkBack.model.user.UserDTO;
import com.ivan.SocialNetworkBack.model.user.UserResponseDTO;
import com.ivan.SocialNetworkBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authMenager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private Cloudinary cloudinary;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(10);

    @Override
    public User register(UserDTO userDto) throws IOException {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        if (userDto.getProfileImage() != null && !userDto.getProfileImage().isEmpty()) {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", "profile-images");
            options.put("resource_type", "auto");

            Map uploadResult = cloudinary.uploader().upload(userDto.getProfileImage().getBytes(), options);
            user.setProfileImageUrl((String) uploadResult.get("url"));
        }

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


    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public List<UserResponseDTO> searchUsers(String query) {
        List<User> users = userRepository.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
            query, query, query);

        return users.stream()
            .map(user -> new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getBio(),
                user.getProfileImageUrl(),
                user.getFollowersCount(),
                user.getFollowingCount()
            ))
            .collect(Collectors.toList());
    }
    @Override
    public UserResponseDTO convertToUserResponseDTO(User user) {
        return new UserResponseDTO(user.getId(),user.getName(),user.getLastname(),user.getUsername(),user.getEmail()
        ,user.getRole(),user.getBio(),user.getProfileImageUrl(),user.getFollowersCount(),user.getFollowingCount());
    }

    public String findProfileImageUrlByUsername(String username) {
        return userRepository.findProfileImageUrlByUsername(username)
                .orElse(null); // Vraća null ako korisnik nije pronađen
    }

}
