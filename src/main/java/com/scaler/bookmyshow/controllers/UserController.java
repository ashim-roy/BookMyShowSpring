package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.SignUpRequestDto;
import com.scaler.bookmyshow.dtos.SignUpResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.User;
//import com.scaler.bookmyshow.repositories.UserRepository;
import com.scaler.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import java.net.UnknownServiceException;
//import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpResponseDto SignUp(SignUpRequestDto signUpRequestDto) throws Exception {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

            // if no exception do success
            signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            signUpResponseDto.setUserId(user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signUpResponseDto;
    }
}
