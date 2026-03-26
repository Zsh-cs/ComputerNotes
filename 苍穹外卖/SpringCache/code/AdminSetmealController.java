package com.sky.controller.admin;

import com.sky.constant.RedisKeyPrefix;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.page.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理模块
 */
@RestController("adminSetmealController")
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    // 新增套餐和对应的菜品
    @PostMapping
    @ApiOperation("新增套餐和对应的菜品")
    @CacheEvict(cacheNames = RedisKeyPrefix.SETMEAL_PREFIX, key = "#setmealDTO.categoryId")// 清理缓存
    public Result saveWithDish(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐：{}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    // 套餐分页查询
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("套餐分页查询，参数为：{}\n", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }


    // 批量删除套餐
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    @CacheEvict(cacheNames = RedisKeyPrefix.SETMEAL_PREFIX, allEntries = true)// 清理全部缓存
    public Result deleteBatch(@RequestParam List<Long> ids) {
        log.info("要删除的套餐id列表：{}", ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }


    // 根据id查询套餐信息
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("查询id={}的套餐", id);
        SetmealVO setmealVO = setmealService.getWithDishesById(id);
        return Result.success(setmealVO);
    }


    // 修改套餐
    @PutMapping
    @ApiOperation("修改套餐信息")
    @CacheEvict(cacheNames = RedisKeyPrefix.SETMEAL_PREFIX, allEntries = true)// 清理全部缓存
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐信息为：{}", setmealDTO);
        setmealService.updateWithDishes(setmealDTO);
        return Result.success();
    }


    // 起售停售套餐
    @PostMapping("/status/{status}")
    @ApiOperation("起售停售套餐")
    @CacheEvict(cacheNames = RedisKeyPrefix.SETMEAL_PREFIX, allEntries = true)// 清理全部缓存
    public Result changeStatus(@PathVariable Integer status, Long id) {
        log.info("准备{}id={}的套餐", status.equals(StatusConstant.ENABLE) ? "起售" : "停售", id);
        setmealService.changeStatus(status, id);
        return Result.success();
    }

}
