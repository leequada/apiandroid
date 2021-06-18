package com.namph.security.service.mapper;

import com.namph.security.domain.User;
import com.namph.security.dto.request.LoginDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {})
public interface LoginMapper extends EntityMapper<LoginDto, User> {
}
