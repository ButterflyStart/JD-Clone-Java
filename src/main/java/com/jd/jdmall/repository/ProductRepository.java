package com.jd.jdmall.repository;

import com.jd.jdmall.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}