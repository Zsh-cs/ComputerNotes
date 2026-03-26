package com.sky.task;

import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 订单定时任务类：定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderService orderService;

    // 定时处理超时未付款的订单
    @Scheduled(cron = "0 * * * * ?")// 每分钟的0秒触发一次
    public void handleTimeoutOrders() {
        log.info("每分钟定时处理超时未付款的订单：{}", LocalDateTime.now());
        orderService.cancelTimeoutOrders();
    }

    // 定时处理一直在派送中的订单
    @Scheduled(cron = "0 0 1 * * ? ")// 每日凌晨1点触发一次
    public void handleDeliveryOrders() {
        log.info("每日凌晨1点定时处理一直在派送中的订单：{}", LocalDateTime.now());
        orderService.completeDeliveryOrders();
    }
}
