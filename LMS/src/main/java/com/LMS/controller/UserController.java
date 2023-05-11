package com.LMS.controller;

import com.LMS.dto.UserRequestDto;
import com.LMS.dto.UserResponseDto;
import com.LMS.internationalization.LocaleResolverConfig;
import com.LMS.response.Response;
import com.LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LocaleResolverConfig localeResolverConfig;
//---------------------------------save user-------------------
    @PostMapping("/save")
    public ResponseEntity<Response> saveUser(@RequestBody UserRequestDto userRequestDto, @RequestHeader(required = false, value = "Accept-Language", defaultValue = "") String language){

        Locale locale = StringUtils.hasText(language) ? new Locale(language) : Locale.getDefault();
    UserResponseDto addUser = userService.createUser(userRequestDto,language);
        Response<UserResponseDto> userResponse = new Response<>(HttpStatus.ACCEPTED, addUser);
        return new ResponseEntity<Response>(userResponse,HttpStatus.CREATED);
    }

//----------------------------update user--------------------------------
    @PutMapping("/get/id={id}")
    public ResponseEntity<Response> updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable long id) {
        UserResponseDto updateUser = userService.updateUser(userRequestDto,id);
        Response<UserResponseDto> userResponse = new Response<>(HttpStatus.ACCEPTED, updateUser);

        return new ResponseEntity<Response>(userResponse,HttpStatus.CREATED);
    }

//------------------------------get all user-------------------------------
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAll(){
        List<UserResponseDto> users = userService.getAllUser();
        Response<List> respondUser= new Response<>(HttpStatus.OK,users);
        return new ResponseEntity<Response>(respondUser,HttpStatus.OK);
    }

//------------------------ get by id -----------------------------
    @GetMapping("/get/id={id}")
    public ResponseEntity<Response> getById(@PathVariable long id) {
        UserResponseDto userDTORespond = userService.getById(id);
        Response<UserResponseDto> respondUser = new Response<>(HttpStatus.OK,userDTORespond );
        return new ResponseEntity<Response>(respondUser, HttpStatus.OK);
    }

    //--------------------- delete user ------------------------------
    @PutMapping("/delete/id={id}")
    public ResponseEntity<Response> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new Response(HttpStatus.OK,"Deleted Successfully!"), HttpStatus.OK);
    }

    //------------------------ find closed  user -------------------
    @GetMapping("/getAll/closed")
    public ResponseEntity<Response> closedUser(){
        List<UserResponseDto> closedUsers = userService.findAllByClosed();
        Response<List> responseUser = new Response<>(HttpStatus.OK, closedUsers);

        return new ResponseEntity<Response>(responseUser,HttpStatus.OK);

    }
}
