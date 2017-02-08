package com.karakal.config;

import com.karakal.commons.mongo.MongoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Bean
	public MongoDao mongoDao() {
		MongoDao mongoDao = new MongoDao();
		mongoDao.setMongoTemplate(mongoTemplate);
		return mongoDao;
	}
}
