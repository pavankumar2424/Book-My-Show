package com.show.demo.Models;

import com.show.demo.Enums.Genre;
import com.show.demo.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Table(name = "movies")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;
    private double duration;

    @Column(scale = 2)
    private double rating;


    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private Date reliseDate;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showList=new ArrayList<>();


}
