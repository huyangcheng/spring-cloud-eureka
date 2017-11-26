package eureka.hello.service.controler;

import api.model.User;
import api.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorController implements HelloService {
    @Override
    public String hello1(String name) {
        return "Refactor Hello:" + name;
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User() {{
            setName(name);
            setAge(age);
        }};
    }

    @Override
    public String hello(@RequestBody User user) {
        return user.toString();
    }
}
