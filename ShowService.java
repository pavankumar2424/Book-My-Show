package com.show.demo.Services;

import com.show.demo.DTOs.RequestDto.ShowDto;
import com.show.demo.DTOs.RequestDto.ShowSeatDto;
import com.show.demo.Enums.SeatType;
import com.show.demo.Exceptions.MovieNotFound;
import com.show.demo.Exceptions.ShowNotFound;
import com.show.demo.Exceptions.TheaterNotFound;
import com.show.demo.Models.*;
import com.show.demo.Repositories.MovieRepository;
import com.show.demo.Repositories.ShowRepository;
import com.show.demo.Repositories.TheaterRepository;
import com.show.demo.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(ShowDto showDto) throws MovieNotFound,TheaterNotFound{

        Optional<Movie> optionalMovie=movieRepository.findById(showDto.getMovieId());
        if(optionalMovie.isEmpty()){
            throw new MovieNotFound("Movie is not present with this id");
        }
        Optional<Theater> optionalTheater=theaterRepository.findById(showDto.getTheaterId());
        if (optionalTheater.isEmpty()){
            throw new TheaterNotFound("Theater is not regeistered with this id");
        }

        Show show= ShowTransformer.showDtoToEntity(showDto);

        Movie movie=optionalMovie.get();
        Theater theater=optionalTheater.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show=showRepository.save(show);

        movie.getShowList().add(show);
        movieRepository.save(movie);
        theater.getShowList().add(show);
        theaterRepository.save(theater);

        return "Show is added succesfully with this id "+show.getId();

    }

    public String associateSeats(ShowSeatDto showSeatDto) throws ShowNotFound{
        Optional<Show> optionalShow=showRepository.findById(showSeatDto.getShowId());
        if (optionalShow.isEmpty()){
            throw new ShowNotFound("Show is not present with this Id ");
        }

        Show show=optionalShow.get();
        Theater theater=show.getTheater();

        List<Theaterseat> theaterseatList=theater.getTheaterseatList();

        List<Showseat> showseatList=show.getShowseatList();

        for (Theaterseat theaterseat:theaterseatList){

            Showseat showseat=new Showseat();
            showseat.setSeatNo(theaterseat.getSeatNo());
            showseat.setAvailable(true);
            showseat.setFoodAttached(false);

            if (theaterseat.getSeatType().equals(SeatType.CLASSIC)){
                showseat.setPrice(showSeatDto.getPriceOfClassicSeat());
            }else {
                showseat.setPrice(showSeatDto.getPriceOfPremiumSeat());
            }

            showseat.setShow(show);
            showseatList.add(showseat);
        }
        showRepository.save(show);

        return "Seat has been assiociatate with this show id "+show.getId();

    }
}
