package com.Proyect.restaurant.modelo;


import com.Proyect.restaurant.dto.ReadUserDTO;
import com.Proyect.restaurant.modelo.Authority;
import com.Proyect.restaurant.modelo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO readUserDTOToUser(User user);

    default String mapAuthoritiesToString(Authority authority) {
        return authority.getName();
    }

}