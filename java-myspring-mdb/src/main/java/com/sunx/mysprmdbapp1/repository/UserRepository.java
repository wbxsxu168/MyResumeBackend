package com.sunx.mysprmdbapp1.repository;

import com.sunx.mysprmdbapp1.model.JwtUser;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<JwtUser, String> {
    Optional<JwtUser> findByUserName(String userName);
    Optional<JwtUser> findByUserNameOrEmail(String userName, String email); //  need match exactly with model jwtUser definition
    Optional<JwtUser> findByEmail(String email);    
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);    
}