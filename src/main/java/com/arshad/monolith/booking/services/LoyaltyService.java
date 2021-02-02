package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.Loyalty;
import com.arshad.monolith.booking.beans.LoyaltyResponse;

public interface LoyaltyService {
    LoyaltyResponse addOrUpdateLoyalty(final Loyalty loyalty);

    LoyaltyResponse getByUserId(final int userId);
}
