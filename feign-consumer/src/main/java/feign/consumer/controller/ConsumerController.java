package feign.consumer.controller;

import feign.consumer.model.User;
import feign.consumer.service.HelloService;
import feign.consumer.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {


    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @RequestMapping("hello")
    public String hello() {
        return helloService.hello();
    }


    @RequestMapping("hello2")

    public String hello2(){

        StringBuffer sb = new StringBuffer();
        sb.append(helloService.hello1("小胡子"));
        sb.append(helloService.hello("小胡子",24));
        sb.append(helloService.hello(new User(){
            {
                setName("小胡子");
                setAge(24);
            }
        }));

        return sb.toString();
    }

    @RequestMapping("hello3")
    public String hello3(){

        StringBuffer sb = new StringBuffer();
        sb.append(refactorHelloService.hello1("小胡子"));
        sb.append(refactorHelloService.hello("小胡子",24));
        sb.append(refactorHelloService.hello(new api.model.User(){
            {
                setName("小胡子3");
                setAge(24);
            }
        }));

        return sb.toString();
    }
}
