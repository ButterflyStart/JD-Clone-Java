package com.jd.jdmall.service;

import com.jd.jdmall.model.Swiper;
import com.jd.jdmall.repository.SwiperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperService {
    @Autowired
    private SwiperRepository swiperRepository;

    public List<Swiper> getAllSwipers() {
        return swiperRepository.findAll();
    }

    public Swiper getSwiperById(Long id) {
        return swiperRepository.findById(id).orElse(null);
    }

//    public Swiper getSwiperByName(String name) {
//        return swiperRepository.findByName(name).orElseThrow(()->new RuntimeException("Swiper not found"));
//    }

    public Swiper saveSwiper(Swiper swiper) {
        return swiperRepository.save(swiper);
    }

    public Swiper updateSwiper(Swiper swiper) {
        return swiperRepository.save(swiper);
    }

    public void deleteSwiper(Swiper swiper) {
        swiperRepository.delete(swiper);
    }
}
