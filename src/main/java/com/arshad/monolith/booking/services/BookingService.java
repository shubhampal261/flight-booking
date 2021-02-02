package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.Booking;
import com.arshad.monolith.booking.beans.BookingResponse;

import java.util.List;

public interface BookingService {

    List<BookingResponse> getAllBookings();

    BookingResponse getBookingByID(int id);

    BookingResponse addBooking(Booking booking);

    BookingResponse deleteBookingById(int id);
}
