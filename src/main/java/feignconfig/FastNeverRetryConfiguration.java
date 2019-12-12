package feignconfig;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 自定义FeignClient(主要用于以ip请求服务的超时和重试设置)
 *
 * @author: maoyunfei
 * Date: 2018/03/20
 */
@Configuration
public class FastNeverRetryConfiguration {
    @Bean
    public Request.Options options() {
        Request.Options options = new Request.Options(
                200, 150
        );
        return options;
    }

    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

}
