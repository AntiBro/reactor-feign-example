package com.example.demo.feign;

import reactivefeign.FallbackFactory;
import reactor.core.publisher.Mono;

/**
 * @Author huaili
 * @Date 2019/12/11 12:36
 * @Description CISApiFallbackFactory
 **/
public class CISApiFallbackFactory extends FallbackFactoryHelper implements FallbackFactory<CISApi> {
    @Override
    public CISApi apply(Throwable cause) {
        return new CISApi() {
            @Override
            public Mono<String> getFeed() {
                loggerFailure("getFeed", cause);
                return Mono.just("");
            }


        };
    }
}
