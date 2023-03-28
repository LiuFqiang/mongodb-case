package com.pigliu.mongodb.dto.mongo;

import cn.hutool.core.annotation.Alias;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * h5页面
 * @author liufuqiang
 */
@Data
@Document("page")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuickPage {

    @Id
    @JsonProperty("_id")
    private String id;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;


    private String uuid;
    private String title;
    private String coverImage;
    private String description;
    private ShareConfig shareConfig;

    private String script;
    private String author;
    private Integer width;
    private Integer height;
    private Integer pageMode;
    private Integer flipType;
    private Boolean slideNumber;
    private Integer status;
    private Boolean isPublish = false;
    private Boolean isTemplate;
    private List<String> members;
    private List<PageInfo> pages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageInfo {
        private String uuid;
        private String name;
        private List<Element> elements;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Element {
        private String uuid;
        private String elName;
        private List<Map<String, Object>> animations;
        private Map<String, Object> commonStyle;
        private List<Map<String, Object>> events;
        private Map<String, Object> propsValue;
        private String valueType;
        private Boolean isForm;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShareConfig {
        private String coverImage;
        private String title;
        private String description;
        private Boolean shareWx;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", description='" + description + '\'' +
                ", shareConfig=" + shareConfig +
                ", script='" + script + '\'' +
                ", author='" + author + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", pageMode=" + pageMode +
                ", flipType=" + flipType +
                ", slideNumber=" + slideNumber +
                ", status=" + status +
                ", isPublish=" + isPublish +
                ", isTemplate=" + isTemplate +
                ", members=" + members +
                ", pages=" + pages +
                '}';
    }
}
