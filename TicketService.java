package com.show.demo.Services;

import com.show.demo.DTOs.RequestDto.TicketDto;
import com.show.demo.DTOs.ResponceDto.TicketResDto;
import com.show.demo.Exceptions.ShowNotFound;
import com.show.demo.Exceptions.UserNotFound;
import com.show.demo.Models.Show;
import com.show.demo.Models.Showseat;
import com.show.demo.Models.Ticket;
import com.show.demo.Models.User;
import com.show.demo.Repositories.ShowRepository;
import com.show.demo.Repositories.TicketRepository;
import com.show.demo.Repositories.UserRepository;
import com.show.demo.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;
    public TicketResDto bookTicket(TicketDto ticketDto) throws Exception {
        Optional<User> userOptional=userRepository.findById(ticketDto.getUserId());
        if (userOptional.isEmpty()) throw new UserNotFound("User id is not correct");

        User user=userOptional.get();

        Optional<Show> showOptional=showRepository.findById(ticketDto.getShowId());
        if (showOptional.isEmpty()) throw new ShowNotFound("Show id is Not present in the Database");

         Show show=showOptional.get();
        int price=validateAndCalculatePrice(show,ticketDto.getReqSeatList());

        if (price==-1){
            throw new Exception("Selected Seats are already Booked ");
        }

        String bookedSeat=seatListToString(ticketDto.getReqSeatList());
        Ticket ticket=Ticket.builder().totalPrice(price).bookedAt(show.getShowDate())
                .bookedSeats(bookedSeat).build();;

        ticket=ticketRepository.save(ticket);


        user.getTicketList().add(ticket);
        userRepository.save(user);

        show.getTicketList().add(ticket);
        showRepository.save(show);


        TicketResDto ticketResDto= TicketTransformer.getTicketResDto(show,ticket);

        return ticketResDto;
    }

    private String seatListToString(List<String> reqSeatList) {
        String bookedSeat="";
        for (String str:reqSeatList){
            bookedSeat+=str+", ";
        }
        return bookedSeat;
    }

    private int validateAndCalculatePrice(Show show, List<String> reqSeatList) {
      List<Showseat> showseatList=show.getShowseatList();
      int price=0;

      for (Showseat showseat:showseatList){
          if (reqSeatList.contains(showseat.getSeatNo())){
              if (showseat.isAvailable()==false){
                  return -1;
              }else {
                  price+=showseat.getPrice();
                  showseat.setAvailable(false);
              }
          }
      }
      return price;
    }

}
