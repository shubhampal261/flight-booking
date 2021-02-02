package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.Booking;
import com.arshad.monolith.booking.beans.BookingResponse;

import java.util.List;

public interface BookingService {

    public List<BookingResponse> getAllBookings();

    public BookingResponse getBookingByID(int id);

    public BookingResponse addBooking(Booking booking);

    public BookingResponse deleteBookingById(int id);
}
