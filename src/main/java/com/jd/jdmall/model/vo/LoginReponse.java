package com.jd.jdmall.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登录响应参数")
public class LoginReponse{

    @Schema(description = "用户名", required = true, example = "admin")
    private String username;

    @Schema(description = "JWT", required = true, example = "123456")
    private String jwt;

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getjwt() {
        return jwt;
    }

    public void setjwt(String jwt) {
        this.jwt = jwt;
    }
}
