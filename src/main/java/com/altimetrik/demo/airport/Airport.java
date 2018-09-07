package com.altimetrik.demo.airport;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Airport {
    
    private String origin;
    private String destination;
    private long flight_number;
    private String travel_class;
    private double price;
}
