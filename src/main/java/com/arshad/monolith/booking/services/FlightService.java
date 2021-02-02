package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.Flight;
import com.arshad.monolith.booking.beans.FlightResponse;

import java.util.List;

public interface FlightService {

    public List<FlightResponse> getAllFlights();

    public FlightResponse getFlightByID(int id);

    public FlightResponse addFlight(Flight flight);

    public FlightResponse deleteFlightById(int id);
}
