package com.LMS.serviceImpl;

import com.LMS.dto.UserRequestDto;
import com.LMS.dto.UserResponseDto;
import com.LMS.exception.AlreadyExistException;
import com.LMS.exception.NotFoundException;
import com.LMS.internationalization.LocaleResolverConfig;
import com.LMS.model.User;
import com.LMS.model.UserEnum;
import com.LMS.repository.UserRepository;
import com.LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private LocaleResolverConfig localeResolverConfig;

    //########################### create User ##############################
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto, String language) {

        Optional<User> userOptional = userRepo.findByEmail(userRequestDto.getEmail());

        String exceptionMessage;                                                                //creating message
            if(userOptional.isPresent()) {
                exceptionMessage = localeResolverConfig.getMessageSource().getMessage("exception.alreadyExist",null, new Locale(language));
                System.out.println("eroor"+exceptionMessage);
                throw new AlreadyExistException(exceptionMessage);
            }
            User newUser = this.dtoToUser(userRequestDto);
            newUser.setStatus(UserEnum.ACTIVE);

            return this.userToDTO(userRepo.save(newUser));         //converting user into userResponseDto

    }
    //############################## Update user #################################
    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, long id) {
        Optional<User> userOptional = userRepo.findById(userRequestDto.getId());
        if(!userOptional.isPresent()){
            throw new NotFoundException("User not Found");
       }
        if (userOptional.get().isPresentStatus() == false || userOptional.get().getStatus() == UserEnum.CLOSED) {
            throw new  NotFoundException("User cannot be accessed");
        }
        userOptional.get().setName(userRequestDto.getName());
        userOptional.get().setEmail(userRequestDto.getEmail());
        userOptional.get().setAddress(userRequestDto.getAddress());
        userOptional.get().setContactNo(userRequestDto.getContactNo());
        userOptional.get().setStatus(UserEnum.ACTIVE);
        userOptional.get().setPresentStatus(true);
        userOptional.get().setRole(userRequestDto.getRole());

        User updatedUser = userRepo.save(userOptional.get());

        UserResponseDto userResponseDto = new UserResponseDto();

        return userResponseDto;

    }

//----------------------------------Delete User -----------------------------------
    @Override
    public void deleteUser( long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(!optionalUser.isPresent()){
            throw new NotFoundException("User not found");
        }
        optionalUser.get().setPresentStatus(false);
        optionalUser.get().setStatus(UserEnum.CLOSED);
        userRepo.save(optionalUser.get());
    }

    //############################## Get All User ###########################
    @Override
    public List<UserResponseDto> getAllUser() {

        List<User> activeUsers = userRepo.findByStatus(UserEnum.ACTIVE);
        if(activeUsers.isEmpty()){
            throw new NotFoundException("No Users are Present at available");
        }

        List<UserResponseDto> activeUserDtos = new ArrayList<>();

        for(User activeUser : activeUsers){
            activeUserDtos.add(userToDTO(activeUser));
        }
//        activeUsers.stream().forEach(activeUser->activeUserDtos.add(userToDTO(activeUser)));
        return activeUserDtos;
    }
//-------------------------------------- find book by id -----------------------------
    @Override
    public UserResponseDto getById(long id) {
        Optional<User> user = userRepo.findById(id);
            if(!user.isPresent())
            {
                throw new NotFoundException("user not found");
            }
            if(user.get().isPresentStatus()==false || user.get().getStatus()==UserEnum.CLOSED || user.get().getStatus()==null){
                throw new NotFoundException("User is Not Active Or The Account is Closed");
            }
                return new UserResponseDto(user.get());
    }

    //----------------------------- find all closed user -------------------------------

    @Override
    public List<UserResponseDto> findAllByClosed() {

        List<User> closedUser = userRepo.findAllByStatus(UserEnum.CLOSED);
        System.out.println(closedUser);
        if(closedUser.isEmpty()){
            throw new NotFoundException("there is no any closed member are available");
        }

        List<UserResponseDto> closedUsersDtos = new ArrayList<>();

        for(User closed : closedUser){
            closedUsersDtos.add(userToDTO(closed));
        }

        return closedUsersDtos;
    }



    public User dtoToUser(UserRequestDto userDTO){
        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setAddress(userDTO.getAddress());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setContactNo(userDTO.getContactNo());
        newUser.setRole(userDTO.getRole());

        return newUser;
    }

    public UserResponseDto userToDTO(User user){
        UserResponseDto userDTORespond = new UserResponseDto();
        userDTORespond.setId(user.getId());
        userDTORespond.setName(user.getName());
        userDTORespond.setAddress(user.getAddress());
        userDTORespond.setEmail(user.getEmail());
        userDTORespond.setContactNo(user.getContactNo());
        userDTORespond.setRole(user.getRole());

        return userDTORespond;
    }
}
