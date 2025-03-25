package com.jd.jdmall.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Swiper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;
    private Integer itemId;
    private String type;
    private Integer sort;
    private boolean isShow;
    private java.time.LocalDateTime createTime;
    private java.time.LocalDateTime updateTime;
}
