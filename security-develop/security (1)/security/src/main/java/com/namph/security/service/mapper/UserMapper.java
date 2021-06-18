package com.namph.security.service.mapper;


import com.namph.security.domain.User;
import com.namph.security.dto.request.RegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {})
public interface UserMapper extends EntityMapper<RegisterDto, User> {
}
