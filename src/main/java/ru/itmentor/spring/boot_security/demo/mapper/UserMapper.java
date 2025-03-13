package ru.itmentor.spring.boot_security.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmentor.spring.boot_security.demo.dto.request.CreateUser;
import ru.itmentor.spring.boot_security.demo.dto.request.UpdateUser;
import ru.itmentor.spring.boot_security.demo.dto.response.FindUserResponce;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    List<FindUserResponce> toDtoListFromEntityList(List<User> entityList);
    User toEntityFromDto(CreateUser dto);
    void updateUserFromDto(UpdateUser dto, @MappingTarget User entity);
    FindUserResponce toFindUserResponceDtoFromEntity(User user);
}
