package com.arshad.monolith.booking.controller;

import com.arshad.monolith.booking.beans.Flight;
import com.arshad.monolith.booking.beans.FlightResponse;
import com.arshad.monolith.booking.services.FlightService;
import com.arshad.monolith.booking.utils.Constants;
import com.arshad.monolith.booking.utils.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/flight")
public class FlightController {

    @Autowired
    private FlightService flightServiceImpl;

    @GetMapping
    public List<FlightResponse> getAllFlights() {
        return flightServiceImpl.getAllFlights();
    }

    @GetMapping(path = "/{id}")
    public FlightResponse getFlight(@PathVariable int id) {
        FlightResponse flight = flightServiceImpl.getFlightByID(id);
        if (flight == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_FIND_RECORD, "flight", id));
        }
        return flight;
    }

    @PostMapping
    public FlightResponse addFlight(@RequestBody Flight flight) {
        final FlightResponse flightResponse = flightServiceImpl.addFlight(flight);
        return flightResponse;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteFlight(@PathVariable int id) {
        FlightResponse flight = flightServiceImpl.deleteFlightById(id);
        if (flight == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_DELETE_RECORD, "flight", id));
        }
        return ResponseEntity.noContent().build();
    }


}
