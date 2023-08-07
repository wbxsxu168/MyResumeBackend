package com.sunx.mysprmdbapp1.security;

import com.sunx.mysprmdbapp1.model.JwtUser;
import com.sunx.mysprmdbapp1.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		JwtUser user = userRepository.findByUserName(usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with this username or email:" + usernameOrEmail));

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoleIDs()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String roles) {
		// split 1,2,3 to set 1 2 3
		Set<String> roleset = Arrays.stream(roles.split("\\,")) // split on colon
				.map(str -> str.trim()) 						// remove white-spaces
				.collect(Collectors.toSet()); 					// collect to Set
		return roleset.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}
	
}