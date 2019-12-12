package feignconfig;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Description: 自定义FeignClient(主要用于以ip请求服务的超时和重试设置)
 *
 * @author: maoyunfei
 * Date: 2017/6/15
 */
@Configuration
public class CustomerFeignConfiguration {
    @Bean
    public Request.Options options() {
        Request.Options options = new Request.Options(
                500, 1500
        );
        return options;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, SECONDS.toMillis(1), 2);
    }

}
