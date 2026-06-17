package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<Venue> listAll() {
        List<Venue> venues = venueRepository.findAll();
        if (venues.isEmpty()) {
            System.out.println("No venues found.");
            return venues;
        }
        for (Venue venue : venues) {
            System.out.println((venues.indexOf(venue) + 1) + ") " + venue.getName()
                    + " - " + venue.getCity()
                    + " (capacity: " + venue.getCapacity() + ")");
        }
        return venues;
    }

    public void addVenue(String name, String city, Long capacity) {
        venueRepository.save(new Venue(name, city, capacity));
        System.out.println("Venue added");
    }

    public void findByCity(String city) {
        List<Venue> venues = venueRepository.findByCityContainingIgnoreCase(city);
        if (venues.isEmpty()) {
            System.out.println("No venues in: " + city);
            return;
        }
        for (Venue venue : venues) {
            System.out.println(venue.getName() + " - " + venue.getCity()
                    + " (capacity: " + venue.getCapacity() + ")");
        }
    }

    public void findByName(String name) {
        List<Venue> venues = venueRepository.findByNameContainingIgnoreCase(name);
        if (venues.isEmpty()) {
            System.out.println("No venues named: " + name);
            return;
        }
        for (Venue venue : venues) {
            System.out.println(venue.getName() + " - " + venue.getCity()
                    + " (capacity: " + venue.getCapacity() + ")");
        }
    }

    public void findByMinCapacity(Long capacity) {
        List<Venue> venues = venueRepository.findByCapacityGreaterThanEqual(capacity);
        if (venues.isEmpty()) {
            System.out.println("No venues at least: " + capacity);
            return;
        }
        for (Venue venue : venues) {
            System.out.println(venue.getName() + " - " + venue.getCity()
                    + " (capacity: " + venue.getCapacity() + ")");
        }
    }

    public void updateCapacity(Venue venue, Long newCapacity) {
        venue.setCapacity(newCapacity);
        venueRepository.save(venue);
        System.out.println("Capacity updated");
    }

    public void deleteVenue(Venue venue) {
        venueRepository.delete(venue);
        System.out.println("Venue deleted");
    }
}
