package org.wangcan.example.order;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.media.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * @author: wangcan
 * @date: 2021/8/8 10:20
 * 熔断 全局异常处理
 */
@Component
@Slf4j
public class SentinelException implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
        HttpServletResponse response, BlockException e) throws Exception {
        Result result = new Result();
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            result.setMsg("被限流了");
            result.setCode(1);
        } else if (e instanceof DegradeException) {
            result.setMsg("服务降级了");
            result.setCode(2);
        } else if (e instanceof ParamFlowException) {
            result.setMsg("被限流了");
            result.setCode(3);
        } else if (e instanceof SystemBlockException) {
            result.setMsg("被系统保护了");
            result.setCode(4);
        } else if (e instanceof AuthorityException) {
            result.setMsg("被授权了");
            result.setCode(5);
        }

        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(),result);
    }

    @Data
    class Result<T> {
        private Integer code;
        private String msg;
        private T data;
    }
}
