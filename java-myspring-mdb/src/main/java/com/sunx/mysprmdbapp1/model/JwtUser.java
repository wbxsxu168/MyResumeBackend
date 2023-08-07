package com.sunx.mysprmdbapp1.model;

import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.USERACCOUNT_SEQUENCE_NAME;
import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.convertToString;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "Users")
@JsonIgnoreProperties(value = { "createtime", "lastmodifiedtime", "createtimeStr",
		"lastmodifiedtimeStr" }, allowGetters = true, ignoreUnknown = true)
public class JwtUser {
	@Id
	private String id;
	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String roleIDs;
	private boolean isAccountLocked = false;
	private boolean isAccountActive = true;
	private long createtime;
	private long lastmodifiedtime;
	@Transient
	private String createtimeStr;
	@Transient
	private String lastmodifiedtimeStr;
	@Transient
	public static final String SEQUENCE_NAME = USERACCOUNT_SEQUENCE_NAME;

	public JwtUser() {

		this.createtime = java.time.Instant.now().getEpochSecond();
		this.lastmodifiedtime = this.createtime;

		this.createtimeStr = convertToString(new Date(this.createtime * 1000));
		this.lastmodifiedtimeStr = convertToString(new Date(this.lastmodifiedtime * 1000));

		this.roleIDs = "3"; // default User role; 2,3
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleIDs(String roleIDs) {
		this.roleIDs = roleIDs;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getRoleIDs() {
		return roleIDs; // 2,3
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAccountActive() {
		return this.isAccountActive;
	}

	public boolean isAccountLocked() {
		return this.isAccountLocked;
	}

	public String getCreateTime() {
		return this.createtimeStr;
	}

	public String getLastModifiedTime() {
		return this.lastmodifiedtimeStr;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * public Set<String> getRoles() { // split 1,2,3 to set 1 2 3 Set<String> ret =
	 * Arrays.stream(this.roleIDs.split("\\,")) // split on colon .map(str ->
	 * str.trim()) // remove white-spaces .collect(Collectors.toSet()); // collect
	 * to Set return ret; }
	 */
//===============================================    
}