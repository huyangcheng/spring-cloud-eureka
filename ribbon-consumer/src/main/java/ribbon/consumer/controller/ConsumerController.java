package ribbon.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ribbon.consumer.model.User;
import ribbon.consumer.service.HelloService;

/**
 * Created by huyangcheng on 2017/11/17.
 */
@RestController
public class ConsumerController {


    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {

        return helloService.helloService();
    }

    @RequestMapping("hello2")
    public String hello2() {

        StringBuffer sb = new StringBuffer();
        sb.append(helloService.hello1("小胡子"));
        sb.append(helloService.hello("小胡子", 24));
        sb.append(helloService.hello(new User() {
            {
                setName("小胡子");
                setAge(24);
            }
        }));

        return sb.toString();
    }
}
