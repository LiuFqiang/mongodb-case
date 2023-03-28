package com.pigliu.mongodb.service;

import com.pigliu.mongodb.dto.mongo.QuickPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageService extends MongoRepository<QuickPage, String> {
}