package com.LMS.service;

import com.LMS.dto.UserRequestDto;
import com.LMS.dto.UserResponseDto;

import java.util.List;

public interface UserService {
//    Response response = new Response();
    public UserResponseDto createUser(UserRequestDto userRequestDto, String language);

    public UserResponseDto updateUser(UserRequestDto userRequestDto, long id);

    public void deleteUser(long id);

    public List<UserResponseDto> getAllUser();

    public UserResponseDto getById(long id);

    public List<UserResponseDto> findAllByClosed();
}
