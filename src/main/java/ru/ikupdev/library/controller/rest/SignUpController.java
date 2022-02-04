package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.dto.JwtAuthDto;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.service.ISignUpService;
import ru.ikupdev.library.service.IUserService;

import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH)
@Api(value = "Sign up controller", tags = {"1. Регистрация пользователя"})
public class SignUpController {
    private final ISignUpService signUpService;
    private final IUserService userService;

    @ApiOperation(value = "Регистрация пользователя", response = JwtAuthDto.class)
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody UserRequestDto dto) {
        if (userService.findByLogin(dto.getLogin()) != null)
            throw new IllegalArgumentException("User with login " + dto.getLogin() + " already exist!");
        if (userService.findByEmail(dto.getEmail()) != null)
            throw new IllegalArgumentException("User with email " + dto.getEmail() + " already exist!");
        UserView userView = signUpService.signUp(dto);
        return ResponseEntity.ok(userView);
    }

}