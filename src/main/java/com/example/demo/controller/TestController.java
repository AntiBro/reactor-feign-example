package com.example.demo.controller;

import com.example.demo.feign.CISApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author huaili
 * @Date 2019/12/10 14:02
 * @Description TODO
 **/
@RestController
public class TestController {

    @Autowired
    CISApi CISApi;

    @RequestMapping("/test")
   public Flux test() {

        int count = 15;

        List<Mono> list = new ArrayList();
        Flux flux = Flux.empty();
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        long t0 = System.currentTimeMillis();
        for(int i=0;i<15;i++){
            Mono ret = CISApi.getFeed();
            flux = flux.concatWith(ret);
        }

        return flux;
    }



}
