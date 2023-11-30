package com.show.demo.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeatDto {

    private int noOfSeatsIn1Row;

    private int nofOfClassicSeats;

    private int noOfPremiumSeats;

    private String location;
}
