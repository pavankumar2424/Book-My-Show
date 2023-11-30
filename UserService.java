package com.show.demo.Services;

import com.show.demo.DTOs.RequestDto.UserDto;
import com.show.demo.Exceptions.UserNotFound;
import com.show.demo.Models.User;
import com.show.demo.Repositories.UserRepository;
import com.show.demo.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String addUser(UserDto userDto) {
        User user= UserTransformer.UserDtoToEntity(userDto);
        userRepository.save(user);
        return "User has been added Succesfully:";
    }

    public UserDto getOldestUser() throws UserNotFound {
        List<User> userList=userRepository.findAll();
        int max=0;
        User oldestUser=null;
        for (User user:userList){
            if (max<user.getAge()){
                max=user.getAge();
                oldestUser=user;
            }
        }
        if (oldestUser==null){
            throw new UserNotFound("There is no users in Our DataBase ");
        }

        UserDto userDto=UserTransformer.UserEntityToDto(oldestUser);
        return userDto;
    }

    public List<UserDto> userGreaterThanAge(Integer age) {
      List<User> userList=userRepository.userGreaterThanAge(age);

      List<UserDto> userDtos=new ArrayList<>();
      for (User user:userList){
          userDtos.add(UserTransformer.UserEntityToDto(user));
      }
        return userDtos;
    }
}
