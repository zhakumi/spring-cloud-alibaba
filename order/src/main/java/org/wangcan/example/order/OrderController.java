package org.wangcan.example.order;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.wangcan.example.order.feign.StockService;

/**
 * @author: wangcan
 * @date: 2021/8/1 10:12
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private StockService stockService;

    @GetMapping("/insert")
    public void test() {
        System.out.println("下单");
//        restTemplate.getForObject("http://127.0.0.1:8081/cloud-stock/update", String.class);
        // 服务发现调用
        restTemplate.getForObject("http://cloud-stock/stock/update", String.class);
    }


    @GetMapping("/feignInsert")
    public void feignTest() {
        System.out.println("feign下单");
        // 服务发现调用
        log.info("feign");
        stockService.update();
    }


    @GetMapping("/header")
    public String header(@RequestHeader("X-Request-red") String header,
        @RequestParam String color) throws InterruptedException {
        Thread.sleep(10000);
        log.info(color);
        return header;
    }

    @GetMapping("/hello")
    // 单个熔断 异常处理 已经改为全局异常处理
//    @SentinelResource(value = "hello", blockHandler = "helloBlockException")
    public String hello() throws InterruptedException {
        Thread.sleep(10000);
        log.info("hello");
        return "hello";
    }

    /**
     * 限流与阻塞处理
     *
     * @param ex
     * @return
     */
    public String helloBlockException(BlockException ex) {
        return "熔断";
    }


    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/get")
    public String get() {
        return "get";
    }


}
