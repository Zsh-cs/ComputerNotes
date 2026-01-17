package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.PasswordModifyDTO;
import com.sky.dto.page.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理模块
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    // 员工登录
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJwt(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        // 构建EmployeeLoginVO对象
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();
        return Result.success(employeeLoginVO);
    }


    // 员工退出登录
    @PostMapping("/logout")
    @ApiOperation("员工退出登录")
    public Result logout() {
        return Result.success();
    }


    // 新增员工
    @PostMapping
    @ApiOperation("新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }


    // 修改密码
    @PutMapping("/editPassword")
    @ApiOperation("修改密码")
    public Result modifyPassword(@RequestBody PasswordModifyDTO passwordModifyDTO){
        passwordModifyDTO.setEmpId(BaseContext.getCurrentId());
        log.info("准备修改id={}的员工的登录密码",passwordModifyDTO.getEmpId());
        employeeService.modifyPassword(passwordModifyDTO);
        return Result.success();
    }


    // 员工分页查询
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询，参数为：{}\n", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


    // 启用禁用员工账号
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result changeStatus(@PathVariable Integer status, Long id) {
        log.info("准备{}id={}的员工账号", status.equals(StatusConstant.ENABLE) ? "启用" : "禁用", id);
        employeeService.changeStatus(status, id);
        return Result.success();
    }


    // 根据id查询员工信息
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工")
    public Result<EmployeeDTO> getById(@PathVariable Long id) {
        log.info("查询id={}的员工", id);
        EmployeeDTO employeeDTO = employeeService.getById(id);
        return Result.success(employeeDTO);
    }


    // 修改员工信息
    @PutMapping
    @ApiOperation("修改员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("修改员工信息为：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

}
