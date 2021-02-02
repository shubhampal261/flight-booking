package com.arshad.monolith.booking.serviceImpl;

import com.arshad.monolith.booking.beans.Flight;
import com.arshad.monolith.booking.beans.FlightResponse;
import com.arshad.monolith.booking.mapper.FlightMapper;
import com.arshad.monolith.booking.repo.FlightJPARepository;
import com.arshad.monolith.booking.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightJPARepository flightRepository;

    public List<FlightResponse> getAllFlights() {
        List<FlightResponse> flightList = FlightMapper.INSTANCE.mapToFlightResponseModelList(flightRepository.findAll());
        return flightList;
    }

    public FlightResponse getFlightByID(int id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            return FlightMapper.INSTANCE.mapToFlightResponseModel(flightOptional.get());
        }
        return null;
    }

    public FlightResponse addFlight(Flight flight) {
        flight = flightRepository.save(flight);
        return FlightMapper.INSTANCE.mapToFlightResponseModel(flight);
    }

    @Override
    public FlightResponse deleteFlightById(int id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            flightRepository.delete(flight);
            return FlightMapper.INSTANCE.mapToFlightResponseModel(flight);
        }
        return null;
    }

}

