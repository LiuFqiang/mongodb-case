package com.pigliu.mongodb.dao;


import com.pigliu.mongodb.dto.BookInfo;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author liufuqiang
 */
@Service
public interface BookMongoRepository extends MongoRepository<BookInfo, String> {

    /**
     * 分页查询
     * @param bookName
     * @param pageable
     * @return
     */
    Page<BookInfo> findByShowNameLike(String bookName, Pageable pageable);

    /**
     * 根据书籍名称查询
     * @param bookName 书籍名称
     * @return
     */
    Optional<BookInfo> findTop2ByShowName(String bookName);

    /**
     * 分片查询
     * @param pageable 分页信息
     * @return
     */
    Slice<BookInfo> findAllByOrderByCreateTimeDesc(Pageable pageable);

    /**
     * 排序查询
     * @param sort 排序信息
     * @return
     */
    List<BookInfo> streamByOrderByCreateTimeDesc(Sort sort);

    /**
     * 是否存在
     * @param showName
     * @return
     */
    Boolean existsByShowName(String showName);

    /**
     * 去重
     * @param showName
     * @return
     */
    List<BookInfo> findShowNameDistinctByShowName(String showName);

    /**
     * 小与创建时间查询
     * @param localDateTime 创建时间
     * @return
     */
    BookInfo findTopByCreateTimeLessThan(LocalDateTime localDateTime);

    /**
     * 条数查询
     * @param authorName
     * @return
     */
    long countByAuthor(String authorName);

    /**
     * stream接收
     * @return
     */
    Streamable<BookInfo> findAllBy();

    /**
     * iterable姐收
     * @return
     */
    Iterable<BookInfo> queryAllByAuthorIsNull();

    /**
     * 自定义查询
     * @param showName
     * @return
     */
    @Query("{showName: ?0}")
    List<BookInfo> customQuery(String showName);
}
