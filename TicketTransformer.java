package com.show.demo.Transformers;

import com.show.demo.DTOs.RequestDto.TicketDto;
import com.show.demo.DTOs.ResponceDto.TicketResDto;
import com.show.demo.Models.Show;
import com.show.demo.Models.Ticket;

public class TicketTransformer {

    public static TicketResDto getTicketResDto(Show show, Ticket ticket){
        TicketResDto ticketResDto=new TicketResDto();
        ticketResDto.setDate(show.getShowDate());
        ticketResDto.setMovieName(show.getMovie().getName());
        ticketResDto.setTheaterName(show.getTheater().getName());
        ticketResDto.setBookedSeat(ticket.getBookedSeats());
        ticketResDto.setShowTime(show.getShowTime());
        return ticketResDto;
    }
}
