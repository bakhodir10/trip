package com.altimetrik.demo.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/find-three-options")
    public Map<String, TripDto> findThreeOptions(@RequestParam String origin, @RequestParam String destination, @RequestParam Boolean nonStop) {
        TripCriteria criteria = TripCriteria.builder().origin(origin).destination(destination).nonStop(nonStop).build();
        return tripService.getThreeOptions(criteria);
    }
}
