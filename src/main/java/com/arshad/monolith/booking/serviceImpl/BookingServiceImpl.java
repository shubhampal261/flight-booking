package com.arshad.monolith.booking.serviceImpl;

import com.arshad.monolith.booking.beans.*;
import com.arshad.monolith.booking.mapper.BookingMapper;
import com.arshad.monolith.booking.mapper.FlightMapper;
import com.arshad.monolith.booking.mapper.UserMapper;
import com.arshad.monolith.booking.repo.BookingJPARepository;
import com.arshad.monolith.booking.services.BookingService;
import com.arshad.monolith.booking.services.FlightService;
import com.arshad.monolith.booking.services.LoyaltyService;
import com.arshad.monolith.booking.services.UserService;
import com.arshad.monolith.booking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingJPARepository bookingRepository;

    @Autowired
    private LoyaltyService loyaltyServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightService flightService;

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

    public BookingResponse addBooking(final Booking booking) {
        bookingRepository.save(booking);
        final BookingResponse bookingResponse = BookingMapper.INSTANCE.mapToBookingResponseModel(booking);

        final UserResponse userResponse = this.userService.getUserByID(booking.getUserId());
        bookingResponse.setUserInfo(userResponse);

        final FlightResponse flightResponse = this.flightService.getFlightByID(booking.getFlight());
        bookingResponse.setFlightInfo(flightResponse);

        this.populateBookingDerivedFields(bookingResponse);

        final double loyaltyPoints = (bookingResponse.getTotalAmount() * Constants.LOYALTY_PERCENT) / 100;
        final Loyalty loyalty = Loyalty.builder().points(loyaltyPoints).userId(bookingResponse.getUserId()).build();
        this.loyaltyServiceImpl.addOrUpdateLoyalty(loyalty);

        return bookingResponse;
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

