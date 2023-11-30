package com.show.demo.Transformers;


import com.show.demo.DTOs.RequestDto.MovieDto;
import com.show.demo.Models.Movie;

public class MovieTransformer {

    public static Movie movieDtoToEntity(MovieDto movieDto){
        Movie movie=Movie.builder().name(movieDto.getName())
                .genre(movieDto.getGenre()).duration(movieDto.getDuration())
                .rating(movieDto.getRating()).language(movieDto.getLanguage())
                .reliseDate(movieDto.getReliseDate())
                .build();

        return movie;
    }
}
