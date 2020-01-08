package com.example.demo;

import com.example.demo.feign.CISApi;
import com.example.demo.feign.CISApiFallbackFactory;
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
    public CISApi baiduApi(){
        String service = "https://kepu.qq.com/";

        ReactiveOptions reactiveOptions = new WebReactiveOptions.Builder().setReadTimeoutMillis(2200L).setWriteTimeoutMillis(2200L).setConnectTimeoutMillis(5000L).build();
        CISApi CISApi = WebReactiveFeign.<CISApi>builder().options(reactiveOptions).fallbackFactory(new CISApiFallbackFactory()).target(CISApi.class, service);
        return CISApi;
    }
}
