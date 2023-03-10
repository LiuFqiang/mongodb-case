package com.pigliu.mongodb.service;

import com.pigliu.mongodb.dao.BookMongoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author liufuqiang
 */
@Slf4j
@Service
@AllArgsConstructor
public class MongoService {

    private final MongoTemplate mongoTemplate;

    private final BookMongoRepository bookMongoRepository;
}
