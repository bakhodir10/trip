package com.altimetrik.demo.trip;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripCriteria {

    private String origin;
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate; // 2018-12-25
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate; // 2018-12-27
    private Boolean nonStop;
    private Date checkIn;
    private Date checkOut;

}
