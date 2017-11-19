package eureka.hello.service.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by huyangcheng on 2017/11/16.
 */
@RestController
public class HelloController {


    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Qualifier("discoveryClient")
    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String index() throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:{}", sleepTime);
        Thread.sleep(sleepTime);

        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello, host:{},port:{},service_id:{}", instance.getHost(), instance.getPort(), instance.getServiceId());
        return "Hello World";
    }
}
