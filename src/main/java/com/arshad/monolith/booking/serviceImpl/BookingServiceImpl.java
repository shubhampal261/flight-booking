package com.arshad.monolith.booking.serviceImpl;

import com.arshad.monolith.booking.beans.Booking;
import com.arshad.monolith.booking.beans.BookingResponse;
import com.arshad.monolith.booking.mapper.BookingMapper;
import com.arshad.monolith.booking.mapper.FlightMapper;
import com.arshad.monolith.booking.mapper.UserMapper;
import com.arshad.monolith.booking.repo.BookingJPARepository;
import com.arshad.monolith.booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingJPARepository bookingRepository;

    public List<BookingResponse> getAllBookings() {
        List<BookingResponse> bookingList = BookingMapper.INSTANCE.mapToBookingResponseModelList(bookingRepository.findAll());
        return bookingList;
    }

    public BookingResponse getBookingByID(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            return BookingMapper.INSTANCE.mapToBookingResponseModel(bookingOptional.get());
        }
        return null;
    }

    public BookingResponse addBooking(Booking booking) {
        booking = bookingRepository.save(booking);
        return BookingMapper.INSTANCE.mapToBookingResponseModel(booking);
    }

    @Override
    public BookingResponse deleteBookingById(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            bookingRepository.delete(booking);
            return BookingMapper.INSTANCE.mapToBookingResponseModel(booking);
        }
        return null;
    }

}

