package com.jd.jdmall.util;

import java.time.LocalDateTime;

public class ApiResponseMy<T> {
    private int code; // 状态码
    private String message; // 响应消息
    private T data; // 响应的具体数据
    private LocalDateTime timestamp; // 响应的时间戳

    // 构造函数
    public ApiResponseMy(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getter 方法
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}