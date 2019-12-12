package com.example.demo.feign;

import feign.RequestLine;
import reactor.core.publisher.Mono;

/**
 * @Author huaili
 * @Date 2019/12/10 13:59
 * @Description TODO
 **/
//@ReactiveFeignClient(value = "baiduapi",url = "http://www.baidu.com",configuration = CustomerFeignConfigurationWithoutRetry.class)
public interface BaiduApi {

    @RequestLine("GET /")
    Mono<String> getHome();

}
