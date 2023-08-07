package com.sunx.mysprmdbapp1.model;

import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.convertToString;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "UserRoles")
@JsonIgnoreProperties(value = { "createtime", "lastmodifiedtime", "createtimeStr",
		"lastmodifiedtimeStr" }, allowGetters = true, ignoreUnknown = true)
public class JwtUserRole {
	@Id
	private String id;
	private String roleID; // 1,2,3,4
	private String roleName; // ADMIN; USERADMIN; USER;GUEST
	private long createtime;
	private long lastmodifiedtime;
	@Transient
	private String createtimeStr;
	@Transient
	private String lastmodifiedtimeStr;

	public JwtUserRole() {

		this.roleID = "3"; // default
		this.roleName = "USER"; // default
		this.createtime = java.time.Instant.now().getEpochSecond();
		this.lastmodifiedtime = this.createtime;

		this.createtimeStr = convertToString(new Date(this.createtime * 1000));
		this.lastmodifiedtimeStr = convertToString(new Date(this.lastmodifiedtime * 1000));
	}

	// Get/Set Auto generated here:
	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getRoleID() {
		return this.roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getLastmodifiedtime() {
		return this.lastmodifiedtime;
	}

	public void setLastmodifiedtime(long lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	public String getCreatetimeStr() {
		return this.createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getLastmodifiedtimeStr() {
		return this.lastmodifiedtimeStr;
	}

	public void setLastmodifiedtimeStr(String lastmodifiedtimeStr) {
		this.lastmodifiedtimeStr = lastmodifiedtimeStr;
	}
}