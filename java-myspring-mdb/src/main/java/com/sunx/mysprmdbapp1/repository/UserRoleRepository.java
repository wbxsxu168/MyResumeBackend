package com.sunx.mysprmdbapp1.repository;

import com.sunx.mysprmdbapp1.model.JwtUserRole;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoleRepository extends MongoRepository<JwtUserRole, String> {
    Optional<JwtUserRole> findByroleName(String roleName);
    Optional<JwtUserRole> findByroleID(String roleID);   
}
