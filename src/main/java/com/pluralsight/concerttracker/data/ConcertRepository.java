package com.pluralsight.concerttracker.data;


import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Query("SELECT c FROM Concert c WHERE c.concert_year = :year")
    List<Concert> findByYear(int year);

    @Query("SELECT c FROM Concert c WHERE c.ticket_price <= :maxPrice")
    List<Concert> findByMaxPrice(double maxPrice);

    @Query("SELECT c FROM Concert c WHERE c.ticket_price BETWEEN :minPrice AND :maxPrice")
    List<Concert> findByPriceRange(double minPrice, double maxPrice);

    @Query("SELECT c FROM Concert c WHERE c.ticket_price <= :maxPrice AND c.concert_year >= :minYear")
    List<Concert> findByMaxPriceAndEarliestYear(@Param("maxPrice")double maxPrice,@Param("minYear") int minYear);

    List<Concert> findByArtist(Artist artist);
    List<Concert> findByVenue(Venue venue);
    List<Concert> findByVenue_City(String city);
}