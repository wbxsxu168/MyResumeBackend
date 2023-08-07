package com.sunx.mysprmdbapp1.services;

import com.sunx.mysprmdbapp1.model.DBsequence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Sequence generator service implementation.
 */
@Service
public class SeqIDGenServiceImpl implements SeqIDGenService {

	private static final Logger LOGGER = LogManager.getLogger(SeqIDGenServiceImpl.class);
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public String generateSequence(String seqName) {
		LOGGER.debug("Generating new identifier for sequence name: " + seqName);
		DBsequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), DBsequence.class);
		return String.valueOf(!Objects.isNull(counter) ? counter.getSeq() : 1);
	}

}