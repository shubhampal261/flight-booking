package com.arshad.monolith.booking.mapper;

import com.arshad.monolith.booking.beans.Flight;
import com.arshad.monolith.booking.beans.FlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightResponse mapToFlightResponseModel(final Flight flight);

    List<FlightResponse> mapToFlightResponseModelList(final List<Flight> flightList);
}
