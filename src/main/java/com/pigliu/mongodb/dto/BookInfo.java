package com.pigliu.mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author liufuqiang
 */
@Data
@Builder
@Document("book")
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    @Id
    private String id;
    private String showName;
    private String author;
    private UserInfo userInfo;
    private LocalDateTime createTime;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserInfo {
        private String username;
        private String nick;
        private String phone;
    }
}
