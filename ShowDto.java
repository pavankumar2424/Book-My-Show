package com.show.demo.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
   private String time;

   private Date date;

   private Integer movieId;

   private Integer theaterId;

}
