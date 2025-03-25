package com.jd.jdmall.controller;

import com.jd.jdmall.model.User;
import com.jd.jdmall.model.vo.RegisterRequest;
import com.jd.jdmall.repository.SwiperRepository;
import com.jd.jdmall.util.ApiRequestMy;
import com.jd.jdmall.util.ApiResponseMy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@Tag(name = "轮播图管理", description = "轮播图相关的API")
public class SwiperController {
    @Autowired
    private SwiperRepository swiperRepository;

    @PostMapping("/getswiper")
    @Operation(
            summary = "获取轮播图列表",
            description = "获取轮播图列表",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "轮播图请求参数",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ApiRequestMy.class)
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "轮播图成功"),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误")
    })
    public ApiResponseMy<Object> register(@RequestBody ApiRequestMy<Object> o) {
        return new ApiResponseMy<>(200, "Get Swiper List successfully", swiperRepository.findAll());
    }
}
