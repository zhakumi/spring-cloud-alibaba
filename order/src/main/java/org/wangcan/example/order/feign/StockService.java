package org.wangcan.example.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: wangcan
 * @date: 2021/8/1 16:25
 */
@FeignClient(name = "cloud-stock", path = "/stock")
public interface StockService {

    @GetMapping("/update")
    void update();
}
