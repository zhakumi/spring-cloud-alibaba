package org.wangcan.example.order;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
//        restTemplate.getForObject("http://127.0.0.1:8081/stock/update", String.class);
        // 服务发现调用
        restTemplate.getForObject("http://stock/stock/update", String.class);
    }


    @GetMapping("/feignInsert")
    public void feignTest() {
        System.out.println("feign下单");
        // 服务发现调用
        log.info("feign");
        stockService.update();
    }


}
