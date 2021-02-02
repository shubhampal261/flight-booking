package com.arshad.monolith.booking.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightResponse {

    private int id;

    private String name;

    private String origin;

    private String destination;

    private Double rate;

    private Integer capacity;

}
