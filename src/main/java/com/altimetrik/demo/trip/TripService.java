package com.altimetrik.demo.trip;

import com.altimetrik.demo.airport.Airport;
import com.altimetrik.demo.car.Car;
import com.altimetrik.demo.hotel.Hotel;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TripService {
    private final String API_KEY = "iaVjKgMjaCpPE9VXR9Sk0nLwCV2Bipks";
    private HttpEntity entity;

    public TripService() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        entity = new HttpEntity(headers);
    }

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, TripDto> getThreeOptions(TripCriteria criteria) {
        Map<String, TripDto> threeOptions = new HashMap<>();
        TripDto cheapestOne = findCheapestOne(criteria);
        threeOptions.put("cheapest", cheapestOne);
        return threeOptions;
    }

    public TripDto findCheapestOne(TripCriteria criteria) {

        // find two airports whose fare is cheapest;
        Airport airport = findAirport(criteria);

        // find hotel which is cheapest
        Hotel hotel = findHotel(criteria);

        // find car which is cheapest
        Car car = findCar(criteria);

        // build tripDto
        TripDto tripDto = new TripDto();
        tripDto.setAirport(airport);
        tripDto.setHotel(hotel);
        tripDto.setCar(car);
        tripDto.setPrice(airport.getPrice() + hotel.getPrice() + car.getPrice());

        return tripDto;
    }

    private Airport findAirport(TripCriteria criteria) {
        String URL_AIRPORT = "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search" +
                "?apikey=" + API_KEY + "&origin=" + criteria.getOrigin() + "&destination=" + criteria.getDestination() +
                "&departure_date=2018-12-25" + "&return_date=2018-12-31&nonstop=" + criteria.getNonStop() + "&number_of_results=1";

        ResponseEntity<String> airportEntity = restTemplate.exchange(URL_AIRPORT, HttpMethod.GET, entity, String.class);
        String airportJson = airportEntity.getBody();
        ReadContext airportReadContext = JsonPath.parse(airportJson);
        String origin = airportReadContext.read("$.results.[0].itineraries[0].outbound.flights[0].origin.airport");
        String destination = airportReadContext.read("$.results.[0].itineraries[0].outbound.flights[0].destination.airport");
        long flight_number = Long.parseLong(airportReadContext.read("$.results.[0].itineraries[0].outbound.flights[0].flight_number"));
        String travel_class = airportReadContext.read("$.results.[0].itineraries[0].outbound.flights[0].booking_info.travel_class");
        double priceAirport = Double.parseDouble(airportReadContext.read("$.results.[0].fare.total_price"));

        Airport airport = new Airport();
        airport.setOrigin(origin);
        airport.setDestination(destination);
        airport.setFlight_number(flight_number);
        airport.setTravel_class(travel_class);
        airport.setPrice(priceAirport);

        return airport;
    }

    private Hotel findHotel(TripCriteria criteria) {
        String URL_HOTEL = "https://api.sandbox.amadeus.com/v1.2/hotels/search-airport" +
                "?apikey=" + API_KEY + "&location=" + criteria.getDestination() + "&check_in=2018-12-15" +
                "&check_out=2018-12-16&number_of_results=1";

        ResponseEntity<String> hotelEntity = restTemplate.exchange(URL_HOTEL, HttpMethod.GET, entity, String.class);
        String hotelJson = hotelEntity.getBody();
        ReadContext hotelReadContext = JsonPath.parse(hotelJson);
        String hotelName = hotelReadContext.read("$.results.[0].property_name");
        String hotelAddress = hotelReadContext.read("$.results.[0].address.line1");
        double priceHotel = Double.parseDouble(hotelReadContext.read("$.results.[0].total_price.amount"));

        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setAddress(hotelAddress);
        hotel.setPrice(priceHotel);
        return hotel;
    }

    private Car findCar(TripCriteria criteria) {
        String URL_CAR = "https://api.sandbox.amadeus.com/v1.2/cars/search-airport" +
                "?apikey=" + API_KEY + "&location=" + criteria.getDestination() + "&pick_up=2018-12-07" +
                "&drop_off=2018-12-07&provider=ZT";

        ResponseEntity<String> carEntity = restTemplate.exchange(URL_CAR, HttpMethod.GET, entity, String.class);
//        String carJson = carEntity.getBody();
//        ReadContext carReadContext = JsonPath.parse(carJson);
//
//        String context = carReadContext.read("$.results.[0]");
//        JSONParser parser = new JSONParser();
//
//        try {
//            Object object = parser.parse(context);
//            JSONObject jsonObject = (JSONObject) object;
//            String branch_id = (String) jsonObject.get("branch_id");
//            System.out.println(branch_id);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Car car = new Car();
        return car;
    }

    public TripDto findFastestOne() {
        return null;
    }

    public TripDto findComfortOne() {
        return null;
    }
}
