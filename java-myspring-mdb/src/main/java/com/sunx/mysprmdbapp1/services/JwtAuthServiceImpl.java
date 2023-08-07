package com.sunx.mysprmdbapp1.services;

import com.sunx.mysprmdbapp1.model.JwtUserRole;
import com.sunx.mysprmdbapp1.errors.APIErrorType;
import com.sunx.mysprmdbapp1.errors.ApiException;
import com.sunx.mysprmdbapp1.model.JwtUser;
import com.sunx.mysprmdbapp1.reqdto.signinAccountDto;
import com.sunx.mysprmdbapp1.reqdto.signupAccountDto;
import com.sunx.mysprmdbapp1.repository.UserRoleRepository;
import com.sunx.mysprmdbapp1.repository.UserRepository;
import com.sunx.mysprmdbapp1.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthServiceImpl implements JwtAuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private SeqIDGenService seqGenService;

    public JwtAuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           UserRoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(signinAccountDto loginDto) {
    	
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(signupAccountDto registerDto) {

        // check whether username already exists in database
        if(userRepository.existsByUserName(registerDto.getUsername())){
        	throw new ApiException(APIErrorType.USERNAMEOREMAIL_CONFLICT_ERRORS, "\"The input UserName already exists!\"");
        }

        // check whether email already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
             throw new ApiException(APIErrorType.USERNAMEOREMAIL_CONFLICT_ERRORS, "\"The inport Email already exists!\"");
        }

        JwtUser user = new JwtUser();
        user.setId(seqGenService.generateSequence(JwtUser.SEQUENCE_NAME)); 
        user.setFirstName(registerDto.getFirstname());
        user.setLastName(registerDto.getLastname());
        user.setUserName(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        JwtUserRole userRole = 
        		roleRepository.findByroleName("USER").get();  //  private	String roleName;  //ADMIN 1; USER 3;  
        user.setRoleIDs(userRole.getRoleID());
        userRepository.save(user);
        
        return "User Sign up successfully!";
    }
}