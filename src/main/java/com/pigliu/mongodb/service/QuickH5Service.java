package com.pigliu.mongodb.service;

import com.pigliu.mongodb.dto.mongo.QuickPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * h5页面制作
 * @author liufuqiang
 */
@Service
@RequiredArgsConstructor
public class QuickH5Service {

    private final MongoTemplate mongoTemplate;

    private final PageService pageService;

    public Optional<QuickPage> findById(String pageId) {
        return pageService.findById(pageId);
    }



}
