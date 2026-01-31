package com.sky.controller.user;

import com.sky.constant.RedisKeyPrefix;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户端-套餐接口
 */
@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
@Api(tags = "用户端-套餐接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;

    // 根据分类id查询已启用的套餐
    @GetMapping("/list")
    @ApiOperation("根据分类id查询已启用的套餐")
    @Cacheable(cacheNames = RedisKeyPrefix.SETMEAL_PREFIX, key = "#categoryId")// key名称：setmeal::{categoryId}
    public Result<List<Setmeal>> getByCategoryId(Long categoryId) {
        log.info("查询分类id={}的已启用的套餐", categoryId);
        List<Setmeal> setmeals = setmealService.getByCategoryId(categoryId);
        return Result.success(setmeals);
    }


    // 根据套餐id查询套餐包含的菜品
    @GetMapping("/dish/{id}")
    @ApiOperation("根据套餐id查询套餐包含的菜品")
    public Result<List<DishItemVO>> getBySetmealId(@PathVariable Long id) {
        log.info("查询id={}的套餐包含的菜品", id);
        List<DishItemVO> dishItemVOs = dishService.getBySetmealId(id);
        return Result.success(dishItemVOs);
    }
}
