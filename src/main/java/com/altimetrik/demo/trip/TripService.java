package com.altimetrik.demo.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TripService {
    private final String API_KEY = "iaVjKgMjaCpPE9VXR9Sk0nLwCV2Bipks";

    @Autowired
    private RestTemplate restTemplate;

    public TripDto findFastestOne() {
//        this.restTemplate.getForObject()
        return null;
    }

    public TripDto findCheapestOne() {

        return null;
    }

    public TripDto findComfortOne() {

        return null;
    }
}
