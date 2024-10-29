package com.phrms.HealthCareSystem.mapper;

import com.phrms.HealthCareSystem.dto.UserDto;
import com.phrms.HealthCareSystem.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
}
