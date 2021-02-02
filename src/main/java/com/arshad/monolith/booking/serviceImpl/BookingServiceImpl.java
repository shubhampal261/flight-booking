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
        this.populateBookingDerivedFields(bookingList);
        return bookingList;
    }

    public BookingResponse getBookingByID(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            final BookingResponse bookingResponse = BookingMapper.INSTANCE.mapToBookingResponseModel(bookingOptional.get());
            this.populateBookingDerivedFields(bookingResponse);
            return bookingResponse;
        }
        return null;
    }

    private void populateBookingDerivedFields(final List<BookingResponse> bookingResponseList) {
        bookingResponseList.forEach(e -> this.populateBookingDerivedFields(e));
    }

    private void populateBookingDerivedFields(final BookingResponse bookingResponse) {
        bookingResponse.setTotalAmount(bookingResponse.getNumberOfSeats() * bookingResponse.getFlightInfo().getRate());
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

