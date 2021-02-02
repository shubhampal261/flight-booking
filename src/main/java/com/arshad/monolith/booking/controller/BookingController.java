package com.arshad.monolith.booking.controller;

import com.arshad.monolith.booking.beans.Booking;
import com.arshad.monolith.booking.beans.BookingResponse;
import com.arshad.monolith.booking.services.BookingService;
import com.arshad.monolith.booking.utils.Constants;
import com.arshad.monolith.booking.utils.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {


    @Autowired
    private BookingService bookingServiceImpl;

    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingServiceImpl.getAllBookings();
    }

    @GetMapping(path = "/{id}")
    public BookingResponse getBooking(@PathVariable int id) {
        BookingResponse booking = bookingServiceImpl.getBookingByID(id);
        if (booking == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_FIND_RECORD, "booking", id));
        }
        return booking;
    }

    @PostMapping
    public BookingResponse addBooking(@RequestBody Booking booking) {
        final BookingResponse bookingResponse = bookingServiceImpl.addBooking(booking);
        return bookingResponse;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteBooking(@PathVariable int id) {
        BookingResponse booking = bookingServiceImpl.deleteBookingById(id);
        if (booking == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_DELETE_RECORD, "booking", id));
        }
        return ResponseEntity.noContent().build();
    }


}
