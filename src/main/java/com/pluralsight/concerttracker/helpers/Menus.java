package com.pluralsight.concerttracker.helpers;

import org.springframework.stereotype.Service;

@Service
public class Menus {
    public void printMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1) Concerts");
        System.out.println("2) Search concerts");
        System.out.println("3) Artists");
        System.out.println("4) Venues");
        System.out.println("5) Promoters");
        System.out.println("6) Reports");
        System.out.println("0) Quit");
        System.out.print("Choose: ");
    }

    public void printConcerts() {
        System.out.println("\n=== Concerts screen ===");
        System.out.println("1) List all concerts");
        System.out.println("2) View one by id");
        System.out.println("3) Add a concert");
        System.out.println("5) Update price");
        System.out.println("6) Update tickets sold");
        System.out.println("7) Delete");
        System.out.println("0) Back");
    }

    public void searchConcerts() {
        System.out.println("\n=== Search concerts screen ===");
        System.out.println("1) list by year ");
        System.out.println("2) list by artist");
        System.out.println("3) list by venue");
        System.out.println("4) list  by city");
        System.out.println("5) list by maximum price");
        System.out.println("6) list  by price range");
        System.out.println("7) list  by maximum price and an earliest year");
        System.out.println("0) back");
    }

    public void artistsScreen() {
        System.out.println("\n=== Artists screen ===");
        System.out.println("1) list all Artists");
        System.out.println("2) add Artists");
        System.out.println("3) find by genre");
        System.out.println("4) find by name");
        System.out.println("5) update genre");
        System.out.println("6) delete");
        System.out.println("0) back");
    }

    public void venuesScreen() {
        System.out.println("\n=== Venues screen ===");
        System.out.println("1) list all Venues");
        System.out.println("2) add Venue");
        System.out.println("3) find by city");
        System.out.println("4) find by name");
        System.out.println("5) find by minimum capacity");
        System.out.println("6) update capacity");
        System.out.println("7) delete");
        System.out.println("0) back");
    }

    public void promoterScreen() {
        System.out.println("\n=== Promoters screen ===");
        System.out.println("1) list all Promoters");
        System.out.println("2) add Promoters");
        System.out.println("3) find by name");
        System.out.println("4) delete");
        System.out.println("0) back");
    }

    public void reportScreen() {
        System.out.println("\n=== Reports screen ===");
        System.out.println("1) revenue per venue");
        System.out.println("2) busiest venue & artist");
        System.out.println("3) average price by year");
        System.out.println("4) capacity report");
        System.out.println("0) back");
    }
}
