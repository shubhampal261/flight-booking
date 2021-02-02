package com.arshad.monolith.booking.mapper;

import com.arshad.monolith.booking.beans.Loyalty;
import com.arshad.monolith.booking.beans.LoyaltyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface LoyaltyMapper {

    LoyaltyMapper INSTANCE = Mappers.getMapper(LoyaltyMapper.class);

    LoyaltyResponse mapToLoyaltyResponseModel(final Loyalty loyalty);

    List<LoyaltyResponse> mapToLoyaltyResponseModelList(final List<Loyalty> loyaltyList);
}
