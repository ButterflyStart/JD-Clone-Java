package com.jd.jdmall.controller;

import com.jd.jdmall.model.vo.LoginRequest;
import com.jd.jdmall.model.User;
import com.jd.jdmall.model.vo.RegisterRequest;
import com.jd.jdmall.repository.UserRepository;
import com.jd.jdmall.util.ApiRequestMy;
import com.jd.jdmall.util.ApiResponseMy;
import com.jd.jdmall.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping("/register")
    @Operation(
            summary = "用户注册",
            description = "通过用户名和密码进行注册",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "注册请求参数",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ApiRequestMy.class)
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "注册成功"),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误")
    })
    public ApiResponseMy<Object> register(@RequestBody ApiRequestMy<RegisterRequest> registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getData().getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getData().getPassword()));
        userRepository.save(user);

        return new ApiResponseMy<>(200, "User registered successfully", null);
    }


    @PostMapping("/login")
    @Operation(
            summary = "用户登录",
            description = "通过用户名和密码进行登录",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "登录请求参数",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ApiRequestMy.class)
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误")
    })
    public ApiResponseMy<String> login(@RequestBody ApiRequestMy<LoginRequest> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getData().getUsername(), loginRequest.getData().getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        if (!jwt.isEmpty()) {
            return new ApiResponseMy<>(200, "登录成功！", jwt);
        } else {
            return new ApiResponseMy<>(401, "登录失败！用户名或密码错误。", null);
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录", description = "返回是否成功")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // 去掉 "Bearer " 前缀
        jwtUtil.blacklistToken(jwt);
        return ResponseEntity.ok("Logged out successfully");
    }
}