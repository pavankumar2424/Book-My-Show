package com.show.demo.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Integer showId;

    private Integer userId;

    private List<String> reqSeatList;
}
