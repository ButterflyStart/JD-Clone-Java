package com.jd.jdmall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "权限管理测试", description = "权限管理测试相关的API")
public class TestController {

    @GetMapping("/public")
    @Operation(summary = "公用 api", description = "返回是否成功")
    public String publicEndpoint() {
        return "success";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // 仅允许 ADMIN 角色访问
    @Operation(summary = "ADMIN 角色访问api", description = "返回是否成功")
    public String adminEndpoint() {
        return "This is an admin endpoint.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')") // 仅允许 USER 角色访问
    @Operation(summary = "USER 角色访问api", description = "返回是否成功")
    public String userEndpoint() {
        return "This is a user endpoint.";
    }

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('READ')") // 仅允许拥有 READ 权限的用户访问
    @Operation(summary = "READ 权限的用户访问api", description = "返回是否成功")
    public String readEndpoint() {
        return "This is a read endpoint.";
    }

    @GetMapping("/write")
    @PreAuthorize("hasAuthority('WRITE')") // 仅允许拥有 WRITE 权限的用户访问
    @Operation(summary = "WRITE 权限的用户访问api", description = "返回是否成功")
    public String writeEndpoint() {
        return "This is a write endpoint.";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')") // 仅允许拥有 DELETE 权限的用户访问
    @Operation(summary = "DELETE 权限的用户访问api", description = "返回是否成功")
    public String deleteEndpoint() {
        return "This is a delete endpoint.";
    }
}