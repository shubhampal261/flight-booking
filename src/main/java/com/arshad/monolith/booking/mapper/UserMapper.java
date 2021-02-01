package com.arshad.monolith.booking.mapper;

import com.arshad.monolith.booking.beans.User;
import com.arshad.monolith.booking.beans.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse mapToUserResponseModel(final User user);

    List<UserResponse> mapToUserResponseModelList(final List<User> userList);
}
