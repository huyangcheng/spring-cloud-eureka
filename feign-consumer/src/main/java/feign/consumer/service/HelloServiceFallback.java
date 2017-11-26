package feign.consumer.service;

import feign.consumer.model.User;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

@Component
public class HelloServiceFallback implements  HelloService {
    @Override
    public String hello() {
        return "fallback hello";
    }

    @Override
    public String hello1(String name) {
        return "fallback hello " + name;
    }

    @Override
    public User hello(String name, Integer age) {
        User user = new User();

        user.setName("fallback " + name);
        return user;

    }

    @Override
    public String hello(User user) {
        user.setName("fallback ");
        return user.toString();
    }

}
