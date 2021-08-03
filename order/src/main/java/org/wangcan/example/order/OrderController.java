package org.wangcan.example.order;

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
    public String header(@RequestHeader("X-Request-red") String header,@RequestParam String color) {
        System.out.print(color);
        return header;
    }

    public static void main(String[] args) {
        BigDecimal subtract=new BigDecimal(10);
        BigDecimal count = subtract.divide(new BigDecimal(3), 0, BigDecimal.ROUND_UP);
//        int count = subtract.divide(new BigDecimal(3))
//            .setScale(0, BigDecimal.ROUND_UP).intValue();
        System.out.print(count);
    }
}
