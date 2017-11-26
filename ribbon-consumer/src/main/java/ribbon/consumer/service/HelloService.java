package ribbon.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ribbon.consumer.model.User;

import java.net.URI;


@Service
public class HelloService {

    private Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String helloService() {

        long start = System.currentTimeMillis();

        String result = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();

        long end = System.currentTimeMillis();

        logger.info("spend time:{}", end - start);
        return result;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String hello1(String name) {
        return restTemplate.getForEntity("http://hello-service/hello1/?name={name}", String.class, "小胡子").getBody();
    }

    public User hello(String name, Integer age) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("name", name);
        httpHeaders.add("age", String.valueOf(age));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        return restTemplate.exchange("http://hello-service/hello2", HttpMethod.GET, httpEntity, User.class).getBody();

    }

    public String hello(User user) {
        return restTemplate.exchange("http://hello-service/hello3", HttpMethod.POST, new HttpEntity(user), String.class).getBody();
    }

    public String fallback() {
        return "error for fallback";
    }

    public String fallback(String name) {
        return "error for fallback" + name;
    }
}
