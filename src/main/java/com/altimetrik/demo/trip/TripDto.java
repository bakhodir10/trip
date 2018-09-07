package com.altimetrik.demo.trip;

import com.altimetrik.demo.airport.Airport;
import com.altimetrik.demo.car.Car;
import com.altimetrik.demo.hotel.Hotel;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripDto {

    private double price = 0;
    private Airport airport;
    private Hotel hotel;
    private Car car;
}
