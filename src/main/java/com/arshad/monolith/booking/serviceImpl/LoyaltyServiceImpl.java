package com.arshad.monolith.booking.serviceImpl;

import com.arshad.monolith.booking.beans.Loyalty;
import com.arshad.monolith.booking.beans.LoyaltyResponse;
import com.arshad.monolith.booking.mapper.LoyaltyMapper;
import com.arshad.monolith.booking.repo.LoyaltyJPARepository;
import com.arshad.monolith.booking.services.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    @Autowired
    private LoyaltyJPARepository loyaltyRepository;

    @Override
    public LoyaltyResponse getByUserId(final int userId) {
        final Optional<Loyalty> loyaltyOptional = loyaltyRepository.findByUserId(userId);
        if (loyaltyOptional.isPresent()) {
            return LoyaltyMapper.INSTANCE.mapToLoyaltyResponseModel(loyaltyOptional.get());
        }
        return LoyaltyResponse.builder().userId(userId).build();
    }

    @Override
    public LoyaltyResponse addOrUpdateLoyalty(final Loyalty loyalty) {
        final LoyaltyResponse loyaltyResponse = this.getByUserId(loyalty.getUserId());
        if (loyaltyResponse.getId() != 0) {
            loyalty.setId(loyaltyResponse.getId());
            loyalty.setPoints(loyalty.getPoints() + loyaltyResponse.getPoints());
        }
        final Loyalty savedOrUpdatedLoyalty = loyaltyRepository.save(loyalty);
        return LoyaltyMapper.INSTANCE.mapToLoyaltyResponseModel(savedOrUpdatedLoyalty);
    }
}
