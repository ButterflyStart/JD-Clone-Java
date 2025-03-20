package com.jd.jdmall.util;

import java.time.LocalDateTime;

public class ApiRequestMy<T> {
    private T data; // 请求的具体数据
    private LocalDateTime timestamp; // 请求的时间戳
    private String version; // API 版本号

    // 构造函数
    public ApiRequestMy(T data, String version) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.version = version;
    }

    // Getter 和 Setter 方法
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}