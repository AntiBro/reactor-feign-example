package com.example.demo;

import com.example.demo.feign.BaiduApi;
import com.example.demo.feign.BaiduApiFallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.ReactiveOptions;
import reactivefeign.webclient.WebReactiveFeign;
import reactivefeign.webclient.WebReactiveOptions;

/**
 * @Author huaili
 * @Date 2019/12/11 9:58
 * @Description config
 **/
@Configuration
public class ReacterFeignConfigService {

    @Bean
    public BaiduApi baiduApi(){
        String service = "https://kepu.qq.com/";

        ReactiveOptions reactiveOptions = new WebReactiveOptions.Builder().setReadTimeoutMillis(2200L).setWriteTimeoutMillis(2200L).setConnectTimeoutMillis(5000L).build();
        BaiduApi baiduApi = WebReactiveFeign.<BaiduApi>builder().options(reactiveOptions).fallbackFactory(new BaiduApiFallbackFactory()).target(BaiduApi.class, service);
        return baiduApi;
    }
}
