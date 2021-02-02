package com.arshad.monolith.booking.controller;

import com.arshad.monolith.booking.beans.Loyalty;
import com.arshad.monolith.booking.beans.LoyaltyResponse;
import com.arshad.monolith.booking.services.LoyaltyService;
import com.arshad.monolith.booking.utils.Constants;
import com.arshad.monolith.booking.utils.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService loyaltyServiceImpl;

    @GetMapping(path = "/{userId}")
    public LoyaltyResponse getLoyalty(@PathVariable int userId) {
        LoyaltyResponse loyalty = loyaltyServiceImpl.getByUserId(userId);
        if (loyalty == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_FIND_RECORD, "loyalty", userId));
        }
        return loyalty;
    }

}
