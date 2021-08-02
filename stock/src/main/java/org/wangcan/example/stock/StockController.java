package org.wangcan.example.stock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangcan
 * @date: 2021/8/1 10:14
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/update")
    public void update() throws InterruptedException {
//        Thread.sleep(4000);
        System.out.println("stock update");
    }
}
