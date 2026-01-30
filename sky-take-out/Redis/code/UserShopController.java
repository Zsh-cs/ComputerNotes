package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

/**
 * 用户端-店铺营业状态
 */
@RestController("userShopController")// 手动指定bean名称，防止冲突
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "用户端-店铺营业状态")
public class ShopController {

    // 存入Redis的字符串的key名称
    private static final Object SHOP_STATUS="SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    // 通过Redis获取店铺营业状态
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        ValueOperations ops = redisTemplate.opsForValue();
        Integer status = (Integer) ops.get(SHOP_STATUS);
        log.info("用户端获取店铺营业状态为：{}", status.equals(StatusConstant.ENABLE) ? "营业中":"打烊中");
        return Result.success(status);
    }
}
