package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.service.IRoleService;

import java.util.List;

import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/role")
@Api(value = "Role controller", tags = {"4. Api ролей пользователей"})
public class RoleController {
    private final IRoleService roleService;

    @ApiOperation(value = "Получить все роли пользователей", response = RestResponseDto.class)
    @GetMapping("/list")
    public RestResponseDto<List<Role>> findAllRoles() {
        List<Role> allRoles = roleService.findAll();
        return new RestResponseDto<>(allRoles);
    }

    @ApiOperation(value = "Получить роль пользователя по id", response = Role.class)
    @GetMapping("/{id}")
    public Role findRoleById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @ApiOperation(value = "Добавить роль", response = Role.class)
    @PostMapping
    public Role addRole(@Validated @RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @ApiOperation(value = "Удалить роль")
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

}