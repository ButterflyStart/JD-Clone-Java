package com.jd.jdmall.controller;

import com.jd.jdmall.model.User;
import com.jd.jdmall.repository.UserRepository;
import com.jd.jdmall.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "用户管理", description = "用户相关的API")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "返回JWT")
    public String login(@Parameter(description = "用户名", required = true)@RequestParam String username, @Parameter(description = "密码", required = true)@RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录", description = "返回是否成功")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // 去掉 "Bearer " 前缀
        jwtUtil.blacklistToken(jwt);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/register")
    @Operation(summary = "注册", description = "返回是否成功")
    public String register(@Parameter(description = "用户名", required = true)@RequestParam String username, @Parameter(description = "密码", required = true)@RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "User registered successfully";
    }
}