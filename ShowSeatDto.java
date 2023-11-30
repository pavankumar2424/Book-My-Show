package com.show.demo.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDto {

    private Integer showId;
    private Integer priceOfClassicSeat;
    private Integer priceOfPremiumSeat;
}
