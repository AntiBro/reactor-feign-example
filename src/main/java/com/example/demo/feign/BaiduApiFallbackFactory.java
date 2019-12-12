package com.example.demo.feign;

import reactivefeign.FallbackFactory;
import reactor.core.publisher.Mono;

/**
 * @Author huaili
 * @Date 2019/12/11 12:36
 * @Description BaiduApiFallbackFactory
 **/
public class BaiduApiFallbackFactory extends FallbackFactoryHelper implements FallbackFactory<BaiduApi> {
    @Override
    public BaiduApi apply(Throwable cause) {
        return new BaiduApi() {
            @Override
            public Mono<String> getHome() {
                loggerFailure("getHome", cause);
                return Mono.just("");
            }


        };
    }
}
