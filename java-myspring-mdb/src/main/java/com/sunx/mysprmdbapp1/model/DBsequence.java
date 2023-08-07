package com.sunx.mysprmdbapp1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DB sequence model collection.
 */
@Document(collection = "db_sequences")
public class DBsequence {

	@Id
	private String id;

	private long seq;

	public DBsequence() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "DB Sequence{" + "id='" + id + '\'' + ", seq=" + seq + '}';
	}

}