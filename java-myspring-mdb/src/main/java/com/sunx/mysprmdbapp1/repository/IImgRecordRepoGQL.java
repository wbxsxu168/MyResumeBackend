package com.sunx.mysprmdbapp1.repository;

import com.sunx.mysprmdbapp1.model.ImageRecords;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IImgRecordRepoGQL extends MongoRepository<ImageRecords,String> {
	//List<ImageRecords> findById(String id);
}