package com.show.demo.Transformers;

import com.show.demo.DTOs.RequestDto.ShowDto;
import com.show.demo.Models.Show;

public class ShowTransformer {

    public static Show showDtoToEntity(ShowDto showDto){

        Show show=Show.builder().showDate(showDto.getDate()).showTime(showDto.getTime()).build();
        return show;
    }
}
