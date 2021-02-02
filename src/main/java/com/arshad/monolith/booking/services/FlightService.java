package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.Flight;
import com.arshad.monolith.booking.beans.FlightResponse;

import java.util.List;

public interface FlightService {

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightByID(int id);

    FlightResponse addFlight(Flight flight);

    FlightResponse deleteFlightById(int id);
}
