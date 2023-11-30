package com.show.demo.DTOs.ResponceDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResDto {

    private String showTime;

    private Date date;

    private String movieName;

    private String theaterName;

    private String bookedSeat;
}
