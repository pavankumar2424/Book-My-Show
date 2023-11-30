package com.show.demo.Transformers;

import com.show.demo.DTOs.RequestDto.TheaterDto;
import com.show.demo.Models.Theater;

public class TheaterTransformer {

    public static Theater TheaterDtoToEntity(TheaterDto theaterDto){
           Theater theater=Theater.builder().name(theaterDto.getName())
                   .location(theaterDto.getLocation()).build();
           return theater;
    }
}
