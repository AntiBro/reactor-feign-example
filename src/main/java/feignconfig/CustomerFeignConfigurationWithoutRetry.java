package feignconfig;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 自定义FeignClient(主要用于以ip请求服务的超时和重试设置)
 *
 * @author: maoyunfeiq
 * Date: 2017/6/15
 */
@Configuration
public class CustomerFeignConfigurationWithoutRetry {
    @Bean
    public Contract feignConfiguration(){
        return new feign.Contract.Default();
    }

    @Bean
    public Request.Options options() {
        Request.Options options = new Request.Options(
                1200, 1500
        );
        return options;
    }

    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }
    @Bean
    public Logger.Level logger(){
        return Logger.Level.HEADERS;
    }


//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverterObjectFactory;
//    @Bean
//    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
//        return new ProtobufHttpMessageConverter();
//    }
//
//    @Bean
//    public Encoder springEncoder() {
//        return new SpringEncoder(this.messageConverterObjectFactory);
//    }
//
//    @Bean
//    public Decoder springDecoder() {
//        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverterObjectFactory));
//    }
}
