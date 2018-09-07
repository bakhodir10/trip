package com.altimetrik.demo.hotel;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel {
    private String name;
    private String address;
    private double price = 0;
}
