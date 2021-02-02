package com.arshad.monolith.booking.mapper;

import com.arshad.monolith.booking.beans.Booking;
import com.arshad.monolith.booking.beans.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingResponse mapToBookingResponseModel(final Booking booking);

    List<BookingResponse> mapToBookingResponseModelList(final List<Booking> bookingList);
}
