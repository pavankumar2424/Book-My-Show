package com.show.demo.Repositories;


import com.show.demo.Models.Theaterseat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<Theaterseat,Integer> {
}
