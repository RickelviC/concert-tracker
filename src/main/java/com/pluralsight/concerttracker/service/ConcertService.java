package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.ArtistRepository;
import com.pluralsight.concerttracker.data.ConcertRepository;
import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final ArtistRepository artistRepository;
    private final VenueRepository venueRepository;



    public ConcertService(ConcertRepository concertRepository, ArtistRepository artistRepository, VenueRepository venueRepository) {
        this.concertRepository = concertRepository;
        this.artistRepository = artistRepository;
        this.venueRepository = venueRepository;
    }


    public List<Concert> listAll() {
        List<Concert> concerts = concertRepository.findAll();
        if (concerts.isEmpty()) {
            System.out.println("No concerts found");
            return concerts;
        }
        for (Concert concert : concerts) {
            System.out.println((concerts.indexOf(concert) + 1) + ") "
                    + concert.getConcert_year()
                    + " | " + concert.getArtist().getName()
                    + " IN " + concert.getVenue().getName()
                    + " | Promoter: " + concert.getPromoter().getName()
                    + " | Price: $" + concert.getTicket_price()
                    + " | Tickets sold: " + concert.getTicket_sold());
        }
        return concerts;
    }

    public void viewById(Long id) {
        Concert concert = concertRepository.findById(id).orElse(null);
        if (concert == null) {
            System.out.println("No concert with that id: " + id);
            return;
        }
        System.out.println("Year: " + concert.getConcert_year());
        System.out.println("Artist: " + concert.getArtist().getName());
        System.out.println("Venue: " + concert.getVenue().getName());
        System.out.println("Promoter: " + concert.getPromoter().getName());
        System.out.println("Price: $" + concert.getTicket_price());
        System.out.println("Tickets sold: " + concert.getTicket_sold());
    }

    public void addConcert(Artist artist, Venue venue, Promoter promoter, int year, double price, int ticketsSold) {
        if (ticketsSold > venue.getCapacity()) {
            System.out.println("Tickets sold out " + venue.getCapacity());
            return;
        }
        if (price < 0) {
            System.out.println("price needs to be over 0");
            return;
        }
        if (ticketsSold < 0) {
            System.out.println("Tickets sold must be 0 or over");
            return;
        }
        concertRepository.save(new Concert(year, price, ticketsSold, artist, venue, promoter));
        System.out.println("Concert added");
    }

    public void updatePrice(Concert concert, double newPrice) {
        if (newPrice < 0) {
            System.out.println("price needs to be over 0");
            return;
        }
        concert.setTicket_price(newPrice);
        concertRepository.save(concert);
        System.out.println("Price updated");
    }

    public void updateTicketsSold(Concert concert, int newTicketsSold) {
        if (newTicketsSold < 0) {
            System.out.println("Tickets sold must be 0 or over");
            return;
        }
        if (newTicketsSold > concert.getVenue().getCapacity()) {
            System.out.println("Tickets sold out " + concert.getVenue().getCapacity());
            return;
        }
        concert.setTicket_sold(newTicketsSold);
        concertRepository.save(concert);
        System.out.println("Tickets sold updated");
    }

    public void deleteConcert(Concert concert) {
        concertRepository.delete(concert);
        System.out.println("Concert deleted");
    }

    public void findByYear(int year) {
        List<Concert> concerts = concertRepository.findByYear(year);
        if (concerts.isEmpty()) {
            System.out.println("No concerts found for year: " + year);
            return;
        }
        for (Concert concert : concerts) {
            System.out.println(concert.getConcert_year()
                    + " | " + concert.getArtist().getName()
                    + " @ " + concert.getVenue().getName()
                    + " | Price: $" + concert.getTicket_price()
                    + " | Tickets sold: " + concert.getTicket_sold());
        }
    }

    public void findByArtist(String name) {
        List<Artist> artists = artistRepository.findByNameContainingIgnoreCase(name);
        if (artists.isEmpty()) {
            System.out.println("No artists found with name: " + name);
            return;
        }
        for (Artist artist : artists) {
            List<Concert> concerts = concertRepository.findByArtist(artist);
            for (Concert concert : concerts) {
                System.out.println(concert.getConcert_year()
                        + " | " + concert.getArtist().getName()
                        + " @ " + concert.getVenue().getName()
                        + " | Price: $" + concert.getTicket_price()
                        + " | Tickets sold: " + concert.getTicket_sold());
            }
        }
    }

    public void findByVenue(String name) {
        List<Venue> venues = venueRepository.findByNameContainingIgnoreCase(name);
        if (venues.isEmpty()) {
            System.out.println("No venues found with name: " + name);
            return;
        }
        for (Venue venue : venues) {
            List<Concert> concerts = concertRepository.findByVenue(venue);
            for (Concert concert : concerts) {
                System.out.println(concert.getConcert_year()
                        + " | " + concert.getArtist().getName()
                        + " @ " + concert.getVenue().getName()
                        + " | Price: $" + concert.getTicket_price()
                        + " | Tickets sold: " + concert.getTicket_sold());
            }
        }
    }

    public void findByCity(String city) {
        List<Concert> concerts = concertRepository.findByVenue_City(city);
        if (concerts.isEmpty()) {
            System.out.println("No concerts found in city: " + city);
            return;
        }
        for (Concert concert : concerts) {
            System.out.println(concert.getConcert_year()
                    + " | " + concert.getArtist().getName()
                    + " @ " + concert.getVenue().getName()
                    + " | Price: $" + concert.getTicket_price()
                    + " | Tickets sold: " + concert.getTicket_sold());
        }
    }

    public void findByMaxPrice(double maxPrice) {
        List<Concert> concerts = concertRepository.findByMaxPrice(maxPrice);
        if (concerts.isEmpty()) {
            System.out.println("No concerts found at or below price: $" + maxPrice);
            return;
        }
        for (Concert concert : concerts) {
            System.out.println(concert.getConcert_year()
                    + " | " + concert.getArtist().getName()
                    + " @ " + concert.getVenue().getName()
                    + " | Price: $" + concert.getTicket_price()
                    + " | Tickets sold: " + concert.getTicket_sold());
        }
    }

    public void findByPriceRange(double minPrice, double maxPrice) {
        List<Concert> concerts = concertRepository.findByPriceRange(minPrice, maxPrice);
        if (concerts.isEmpty()) {
            System.out.println("No concerts found between $" + minPrice + " and $" + maxPrice);
            return;
        }
        for (Concert concert : concerts) {
            System.out.println(concert.getConcert_year()
                    + " | " + concert.getArtist().getName()
                    + " @ " + concert.getVenue().getName()
                    + " | Price: $" + concert.getTicket_price()
                    + " | Tickets sold: " + concert.getTicket_sold());
        }
    }

    public void findByMaxPriceAndEarliestYear(double maxPrice, int minYear) {
        List<Concert> concerts = concertRepository.findByMaxPriceAndEarliestYear(maxPrice, minYear);
        if (concerts.isEmpty()) {
            System.out.println("No concerts found at or below $" + maxPrice + " from year " + minYear + " onwards.");
            return;
        }
        for (Concert concert : concerts) {
            System.out.println(concert.getConcert_year()
                    + " | " + concert.getArtist().getName()
                    + " @ " + concert.getVenue().getName()
                    + " | Price: $" + concert.getTicket_price()
                    + " | Tickets sold: " + concert.getTicket_sold());
        }
    }
}
