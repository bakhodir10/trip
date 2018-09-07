package com.altimetrik.demo.trip;

import com.altimetrik.demo.city.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripCriteria {
    
    private City origination;
    private City destination;
    private Date startDate;
    private Date duration;
    private ComfortOptions comfortOptions;
}
