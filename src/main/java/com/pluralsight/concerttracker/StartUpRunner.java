package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.helpers.Menus;
import com.pluralsight.concerttracker.models.*;
import com.pluralsight.concerttracker.seeder.DataSeed;
import com.pluralsight.concerttracker.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class StartUpRunner implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);
    private final ArtistService artistService;
    private final ConcertService concertService;
    private final PromoterService promoterService;
    private final VenueService venueService;
    private final DataSeed dataSeed;
    private final Menus menus;


    public StartUpRunner(ArtistService artistService,
                         ConcertService concertService,
                         PromoterService promoterService,
                         VenueService venueService,
                         DataSeed dataSeed, Menus menus) {
        this.artistService = artistService;
        this.concertService = concertService;
        this.promoterService = promoterService;
        this.venueService = venueService;
        this.dataSeed = dataSeed;
        this.menus = menus;
    }

    @Override
    public void run(String... args) {
        dataSeed.loadData();
        boolean running = true;
        while (running) {
            menus.printMenu();
            try {
                switch (scanner.nextInt()) {
                    case 1 -> printConcerts();
                    case 2 -> searchConcerts();
                    case 3 -> artistsScreen();
                    case 4 -> venuesScreen();
                    case 5 -> promoterScreen();
                    case 6 -> reportScreen();
                    case 0 -> running = false;
                    default -> System.out.println("Unknown option.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void artistsScreen() {
        boolean running = true;
        while (running) {
            menus.artistsScreen();

            switch (scanner.nextInt()) {
                case 1 -> artistService.listAll();
                case 2 -> addArtist();
                case 3 -> findArtistByGenre();
                case 4 -> findArtistByName();
                case 5 -> updateArtistGenre();
                case 6 -> deleteArtist();
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void addArtist() {
        scanner.nextLine();
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        artistService.addArtist(name, genre);
    }

    private void findArtistByGenre() {
        scanner.nextLine();
        System.out.print("Enter genre to search: ");
        String genre = scanner.nextLine().trim();
        artistService.findByGenre(genre);
    }

    private void findArtistByName() {
        scanner.nextLine();
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        artistService.findByName(name);
    }

    private void updateArtistGenre() {
        List<Artist> artists = artistService.listAll();
        System.out.print("Choose artist: ");
        scanner.nextLine();
        Artist artist = artists.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        System.out.print("Enter new genre: ");
        String newGenre = scanner.nextLine().trim();
        artistService.updateGenre(artist, newGenre);
    }

    private void deleteArtist() {
        scanner.nextLine();
        List<Artist> artists = artistService.listAll();
        System.out.print("Choose artist number: ");
        Artist artist = artists.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        artistService.deleteArtist(artist);
    }

    public void venuesScreen() {
        boolean running = true;
        while (running) {
            menus.venuesScreen();

            switch (scanner.nextInt()) {
                case 1 -> venueService.listAll();
                case 2 -> addVenue();
                case 3 -> findVenueByCity();
                case 4 -> findVenueByName();
                case 5 -> findVenueByMinCapacity();
                case 6 -> updateVenueCapacity();
                case 7 -> deleteVenue();
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void addVenue() {
        scanner.nextLine();
        System.out.print("Enter venue name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter city: ");
        String city = scanner.nextLine().trim();
        System.out.print("Enter capacity: ");
        Long capacity = Long.parseLong(scanner.nextLine().trim());
        venueService.addVenue(name, city, capacity);
    }

    private void findVenueByCity() {
        scanner.nextLine();
        System.out.print("Enter city: ");
        venueService.findByCity(scanner.nextLine().trim());
    }

    private void findVenueByName() {
        scanner.nextLine();
        System.out.print("Enter name: ");
        venueService.findByName(scanner.nextLine().trim());
    }

    private void findVenueByMinCapacity() {
        scanner.nextLine();
        System.out.print("Enter minimum capacity: ");
        Long capacity = Long.parseLong(scanner.nextLine().trim());
        venueService.findByMinCapacity(capacity);
    }

    private void updateVenueCapacity() {
        scanner.nextLine();
        List<Venue> venues = venueService.listAll();
        System.out.print("Choose venue: ");
        Venue venue = venues.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        System.out.print("Enter new capacity: ");
        Long newCapacity = Long.parseLong(scanner.nextLine().trim());
        venueService.updateCapacity(venue, newCapacity);
    }

    private void deleteVenue() {
        scanner.nextLine();
        List<Venue> venues = venueService.listAll();
        System.out.print("Choose venue: ");
        Venue venue = venues.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        venueService.deleteVenue(venue);
    }

    public void promoterScreen() {
        boolean running = true;
        while (running) {
            menus.promoterScreen();

            switch (scanner.nextInt()) {
                case 1 -> promoterService.listAll();
                case 2 -> addPromoter();
                case 3 -> findPromoterByName();
                case 4 -> deletePromoter();
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void addPromoter() {
        scanner.nextLine();
        System.out.print("Enter promoter name: ");
        String name = scanner.nextLine().trim();
        promoterService.addPromoter(name);
    }

    private void findPromoterByName() {
        scanner.nextLine();
        System.out.print("Enter name: ");
        promoterService.findByName(scanner.nextLine().trim());
    }

    private void deletePromoter() {
        scanner.nextLine();
        List<Promoter> promoters = promoterService.listAll();
        System.out.print("Choose promoter number: ");
        Promoter promoter = promoters.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        promoterService.deletePromoter(promoter);
    }

    public void printConcerts() {
        boolean running = true;
        while (running) {
            menus.printConcerts();
            switch (scanner.nextLine().trim()) {
                case "1" -> concertService.listAll();
                case "2" -> viewConcertById();
                case "3" -> addConcert();
                case "5" -> updateConcertPrice();
                case "6" -> updateConcertTicketsSold();
                case "7" -> deleteConcert();
                case "0" -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void viewConcertById() {
        scanner.nextLine();
        System.out.print("Enter concert id: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        concertService.viewById(id);
    }

    private void addConcert() {
        scanner.nextLine();
        System.out.println("\n=== Select an Artist ===");
        List<Artist> artists = artistService.listAll();
        System.out.print("Choose artist number: ");
        Artist artist = artists.get(Integer.parseInt(scanner.nextLine().trim()) - 1);

        System.out.println("\n=== Select a Venue ===");
        List<Venue> venues = venueService.listAll();
        System.out.print("Choose venue number: ");
        Venue venue = venues.get(Integer.parseInt(scanner.nextLine().trim()) - 1);

        System.out.println("\n=== Select a Promoter ===");
        List<Promoter> promoters = promoterService.listAll();
        System.out.print("Choose promoter number: ");
        Promoter promoter = promoters.get(Integer.parseInt(scanner.nextLine().trim()) - 1);

        System.out.print("Enter concert year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter ticket price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter tickets sold: ");
        int ticketsSold = Integer.parseInt(scanner.nextLine().trim());

        concertService.addConcert(artist, venue, promoter, year, price, ticketsSold);
    }

    private void updateConcertPrice() {
        scanner.nextLine();
        List<Concert> concerts = concertService.listAll();
        System.out.print("Choose concert number: ");
        Concert concert = concerts.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        System.out.print("Enter new price: ");
        double newPrice = Double.parseDouble(scanner.nextLine().trim());
        concertService.updatePrice(concert, newPrice);
    }

    private void updateConcertTicketsSold() {
        scanner.nextLine();
        List<Concert> concerts = concertService.listAll();
        System.out.print("Choose concert number: ");
        Concert concert = concerts.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        System.out.print("Enter new tickets sold: ");
        int newTicketsSold = Integer.parseInt(scanner.nextLine().trim());
        concertService.updateTicketsSold(concert, newTicketsSold);
    }

    private void deleteConcert() {
        scanner.nextLine();
        List<Concert> concerts = concertService.listAll();
        System.out.print("Choose concert number: ");
        Concert concert = concerts.get(Integer.parseInt(scanner.nextLine().trim()) - 1);
        concertService.deleteConcert(concert);
    }

    public void searchConcerts() {
        boolean running = true;
        while (running) {
            menus.searchConcerts();
            switch (scanner.nextLine().trim()) {
                case "1" -> searchByYear();
                case "2" -> searchByArtist();
                case "3" -> searchByVenue();
                case "4" -> searchByCity();
                case "5" -> searchByMaxPrice();
                case "6" -> searchByPriceRange();
                case "7" -> searchByMaxPriceAndEarliestYear();
                case "0" -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void searchByYear() {
        System.out.print("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        concertService.findByYear(year);
    }

    private void searchByArtist() {
        System.out.print("Enter artist name: ");
        concertService.findByArtist(scanner.nextLine().trim());
    }

    private void searchByVenue() {
        System.out.print("Enter venue name: ");
        concertService.findByVenue(scanner.nextLine().trim());
    }

    private void searchByCity() {
        System.out.print("Enter city: ");
        concertService.findByCity(scanner.nextLine().trim());
    }

    private void searchByMaxPrice() {
        System.out.print("Enter maximum price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine().trim());
        concertService.findByMaxPrice(maxPrice);
    }

    private void searchByPriceRange() {
        System.out.print("Enter minimum price: ");
        double minPrice = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter maximum price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine().trim());
        concertService.findByPriceRange(minPrice, maxPrice);
    }

    private void searchByMaxPriceAndEarliestYear() {
        System.out.print("Enter maximum price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter earliest year: ");
        int minYear = Integer.parseInt(scanner.nextLine().trim());
        concertService.findByMaxPriceAndEarliestYear(maxPrice, minYear);
    }

    public void reportScreen() {
        boolean running = true;
        while (running) {
            menus.reportScreen();
            switch (scanner.nextLine().trim()) {
                case "1" -> concertService.revenuePerVenue();
                case "2" -> concertService.busiestVenueAndArtist();
                case "3" -> concertService.averagePriceByYear();
                case "4" -> concertService.capacityReport();
                case "0" -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }
}