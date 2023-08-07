package com.sunx.mysprmdbapp1.model;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.convertToString;
import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.IMGRECORDS_SEQUENCE_NAME;

@Document("imagerecords") // mongoDB collection like table, document like row in classic RDMS
@JsonIgnoreProperties(value = { "createtime", "lastmodifiedtime", "createtimeStr",
		"lastmodifiedtimeStr" }, allowGetters = true, ignoreUnknown = true)
public class ImageRecords {

	@Id
	private String id;
	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Title is mandatory")
	private String title;
	private String description;

	@NotBlank(message = "Category field is mandatory")
	private String category;

	private long createtime;
	private long lastmodifiedtime;

	private String createtimeStr;
	private String lastmodifiedtimeStr;

	@Transient
	public static final String SEQUENCE_NAME = IMGRECORDS_SEQUENCE_NAME;

	public ImageRecords(String id, String name, String title, String description, String category) {

		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.createtime = java.time.Instant.now().getEpochSecond();
		this.lastmodifiedtime = this.createtime;
		this.createtimeStr = convertToString(new Date(this.createtime * 1000));
		this.lastmodifiedtimeStr = convertToString(new Date(this.createtime * 1000));
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setLastModifiedTime(java.time.Instant.now().getEpochSecond());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		setLastModifiedTime(java.time.Instant.now().getEpochSecond());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		setLastModifiedTime(java.time.Instant.now().getEpochSecond());
	}

	public long getCreateTime() {
		return createtime;
	}

	public void setCreateTime(long createtime) {
		this.createtime = createtime;
	}

	public long getLastModifiedTime() {
		return lastmodifiedtime;
	}

	public void setLastModifiedTime(long lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
		setLastModifiedTimeStr(convertToString(new Date(lastmodifiedtime * 1000)));
	}

	public String getCreateTimeStr() {
		return this.createtimeStr;
	}

	public void setCreateTimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getLastModifiedTimeStr() {
		return this.lastmodifiedtimeStr;
	}

	public void setLastModifiedTimeStr(String lastmodifiedtimeStr) {
		this.lastmodifiedtimeStr = lastmodifiedtimeStr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}