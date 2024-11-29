package com.ivan.SocialNetworkBack.repository;

import com.ivan.SocialNetworkBack.model.user.Status;
import com.ivan.SocialNetworkBack.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);

    List<User> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
        String name, String lastname, String username);

    @Query(value = "{ 'username': ?0 }", fields = "{ 'profileImageUrl': 1 }")
    Optional<String> findProfileImageUrlByUsername(String username);


    List<User> findAllByStatus(Status status);
}
