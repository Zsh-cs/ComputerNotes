package com.sky.controller.admin;

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
 * 管理端-店铺营业状态
 */
@RestController("adminShopController")// 手动指定bean名称，防止冲突
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "管理端-店铺营业状态")
public class ShopController {

    // 存入Redis的字符串的key名称
    private static final Object SHOP_STATUS="SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    // 通过Redis设置店铺营业状态
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺营业状态为：{}", status.equals(StatusConstant.ENABLE) ? "营业中":"打烊中");
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(SHOP_STATUS,status);
        return Result.success();
    }


    // 通过Redis获取店铺营业状态
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){
        ValueOperations ops = redisTemplate.opsForValue();
        Integer status = (Integer) ops.get(SHOP_STATUS);
        log.info("管理端获取店铺营业状态为：{}", status.equals(StatusConstant.ENABLE) ? "营业中":"打烊中");
        return Result.success(status);
    }
}
