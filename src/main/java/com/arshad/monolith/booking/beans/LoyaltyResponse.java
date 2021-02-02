package com.arshad.monolith.booking.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoyaltyResponse {
    private int id;

    private int userId;

    private User userInfo;

    private double points;
}
