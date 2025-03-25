package com.jd.jdmall.repository;

import com.jd.jdmall.model.Role;
import com.jd.jdmall.model.Swiper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SwiperRepository extends JpaRepository<Swiper, Long> {
//    Optional<Swiper> findByName(String name);
}
