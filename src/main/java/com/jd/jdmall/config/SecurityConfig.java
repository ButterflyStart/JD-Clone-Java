package com.jd.jdmall.config;

import com.jd.jdmall.filter.JwtAuthenticationFilter;
import com.jd.jdmall.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // 启用方法级别的权限控制
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // 放行认证接口
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll() // 放行Swagger
                        .requestMatchers("/public/**").permitAll() // 放行公共路径
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 仅允许 ADMIN 角色访问
                        .requestMatchers("/api/user/**").hasRole("USER") // 仅允许 USER 角色访问
                        .requestMatchers("/api/public").permitAll() // 放行公共 API
                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults()) // 使用 HTTP Basic 认证
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // 注销 URL
//                        .logoutSuccessUrl("/login?logout") // 注销成功后的跳转页面
//                        .permitAll())
                .userDetailsService(userDetailsService)// 使用自定义 UserDetailsService
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        //TestController测试
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/public").permitAll() // 放行公共 API
//                        .anyRequest().authenticated()) // 其他 API 需要认证
//                .httpBasic(Customizer.withDefaults()) // 使用 HTTP Basic 认证
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // 注销 URL
//                        .logoutSuccessUrl("/login?logout") // 注销成功后的跳转页面
//                        .permitAll())
////                .formLogin(form -> form
////                        .loginPage("/login") // 自定义登录页面
////                        .permitAll())
////                .logout(logout -> logout
////                        .permitAll())
//                .userDetailsService(userDetailsService); // 使用自定义 UserDetailsService
//        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}