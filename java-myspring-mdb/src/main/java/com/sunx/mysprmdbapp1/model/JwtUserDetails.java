package com.sunx.mysprmdbapp1.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = -6270477248729576499L;
	private String userName;
	private String token;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(String userName, String password, String token, List<GrantedAuthority> grantedAuthorities) {
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getToken() {
		return this.token;
	}

}