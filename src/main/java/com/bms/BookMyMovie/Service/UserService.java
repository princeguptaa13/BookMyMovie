package com.bms.BookMyMovie.Service;

import com.bms.BookMyMovie.Dto.UsersDto;
import com.bms.BookMyMovie.Entity.Users;
import com.bms.BookMyMovie.Exception.ResouceNotFoundException;
import com.bms.BookMyMovie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UsersDto createUser(UsersDto usersDto){
        Users users = mapToEntity(usersDto);
        Users savedUser = userRepository.save(users);
        return mapToDto(savedUser);
    }

    public UsersDto getUserById(Long id){
        Users users = userRepository.findById(id)
                .orElseThrow(()->new ResouceNotFoundException("User not found with given id : "+id));
        return mapToDto(users);
    }

    public List<UsersDto> getAllUsers(){
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UsersDto updateUser(Long id , UsersDto usersDto){
        Users users = userRepository.findById(id)
                .orElseThrow(()->new ResouceNotFoundException("User not found with given id : "+id));
        users.setEmail(usersDto.getEmail());
        users.setName(usersDto.getName());
        users.setPhoneNo(usersDto.getPhoneNumber());

        Users updated = userRepository.save(users);
        return mapToDto(updated);
    }

    public void deleteUser(Long id){
        Users users = userRepository.findById(id)
                .orElseThrow(()->new ResouceNotFoundException("User not found with given id : "+id));
         userRepository.delete(users);
    }

    private Users mapToEntity(UsersDto usersDto) {
    Users users = new Users();
        users.setEmail(usersDto.getEmail());
        users.setName(usersDto.getName());
        users.setPhoneNo(usersDto.getPhoneNumber());
        return users;
    }
    private UsersDto mapToDto(Users user){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(user.getId());
        usersDto.setName(user.getName());
        usersDto.setEmail(user.getEmail());
        usersDto.setPhoneNumber(user.getPhoneNo());
        return usersDto;
    }
}
