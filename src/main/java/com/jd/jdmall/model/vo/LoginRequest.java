package com.jd.jdmall.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登录请求参数")
public class LoginRequest {

    @Schema(description = "用户名", required = true, example = "admin")
    private String username;

    @Schema(description = "密码", required = true, example = "123456")
    private String password;

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


