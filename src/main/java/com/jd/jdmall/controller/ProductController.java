package com.jd.jdmall.controller;

import com.jd.jdmall.model.Product;
import com.jd.jdmall.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "商品管理", description = "商品相关的API")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "获取所有商品", description = "返回所有商品的列表")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取商品", description = "返回指定ID的商品")
    public Product getProductById(
            @Parameter(description = "商品ID", required = true)
            @PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(summary = "创建商品", description = "创建一个新的商品")
    public Product createProduct(
            @Parameter(description = "商品信息", required = true)
            @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新商品", description = "更新指定ID的商品信息")
    public Product updateProduct(
            @Parameter(description = "商品ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "商品信息", required = true)
            @RequestBody Product product) {
        product.setId(id);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品", description = "删除指定ID的商品")
    public void deleteProduct(
            @Parameter(description = "商品ID", required = true)
            @PathVariable Long id) {
        productService.deleteProduct(id);
    }
}