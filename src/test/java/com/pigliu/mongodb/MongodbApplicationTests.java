package com.pigliu.mongodb;

import com.pigliu.mongodb.dao.BookMongoRepository;
import com.pigliu.mongodb.dto.BookInfo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.ExecutableFindOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

@SpringBootTest
class MongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookMongoRepository bookMongoRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void save() {
        BookInfo bookInfo = BookInfo.builder().showName("少年歌行").createTime(LocalDateTime.now()).build();
        mongoTemplate.save(bookInfo);
    }

    @Test
    void query() {
        // 查询条数
        long count = mongoTemplate.query(BookInfo.class).count();
        long count1 = mongoTemplate.count(new Query(), BookInfo.class);
        long count2 = bookMongoRepository.countByAuthor("周木楠");
        System.out.println(count);
        //

        // id查询并取第一条
        BookInfo info = BookInfo.builder().id("64098ad03be0a7735ba48519").build();
        Example<BookInfo> example = Example.of(info);
        bookMongoRepository.findBy(example, con -> con.first());

        // 同上
        bookMongoRepository.findOne(example);

        // 字段条件查询
        Criteria criteria = new Criteria();
        criteria.and("bookName").is("少年歌行");
        Query query = new Query(criteria);
        List<BookInfo> bookInfos = mongoTemplate.find(query, BookInfo.class);

        // jpa自定义查询前2条
        bookMongoRepository.findTop2ByShowName("少年歌行");

        // 模糊分页查询
        PageRequest page = PageRequest.of(1, 2);
        Page<BookInfo> pageInfo = bookMongoRepository.findByShowNameLike("少年", Pageable.unpaged());

        // 排序
        Sort createTime = Sort.by("createTime").ascending();
        // 或
        Sort.TypedSort<BookInfo> sort = Sort.sort(BookInfo.class);
        sort.by(BookInfo::getCreateTime).descending();
        bookMongoRepository.findAll(sort);

        // 时间范围查询
        LocalDateTime time = LocalDateTime.of(2023, 3, 9, 16, 0, 0);
        BookInfo than = bookMongoRepository.findTopByCreateTimeLessThan(time);

        // 去重查询
        List<BookInfo> distinct = bookMongoRepository.findShowNameDistinctByShowName("少年歌行");
        System.out.println(1);

        // stream查询
        Streamable<BookInfo> stream = bookMongoRepository.findAllBy();

        stream.forEach(System.out::println);

        Iterable<BookInfo> iterable = bookMongoRepository.queryAllByAuthorIsNull();

        List<BookInfo> infos = bookMongoRepository.customQuery("少年歌行");
        System.out.println(infos);
    }

    @Test
    void pageQuery() {
        // 分页查询
        Query newQuery = new Query().with(Sort.by("createTime").ascending()).with(PageRequest.of(0, 10));
        mongoTemplate.find(newQuery, BookInfo.class);


        // 分片
        Slice<BookInfo> slice = bookMongoRepository.findAllByOrderByCreateTimeDesc(PageRequest.of(0, 4));

        // stream查询
        List<BookInfo> bookInfos = bookMongoRepository.streamByOrderByCreateTimeDesc(Sort.unsorted());

        // 是否存在
        Boolean exists = bookMongoRepository.existsByShowName("少年歌行");



        System.out.println(1);
    }

    @Test
    void update() {
    }
}
