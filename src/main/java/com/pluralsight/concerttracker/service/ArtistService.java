package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.ArtistRepository;
import com.pluralsight.concerttracker.models.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;


    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> listAll() {
        List<Artist> artists = artistRepository.findAll();
        if (artists.isEmpty()) {
            System.out.println("No artists found.");
            return artists;
        }
        for (int i = 0; i < artists.size(); i++) {
            System.out.println((i + 1) + ") " + artists.get(i).getName()
                    + " - " + artists.get(i).getGenre());
        }
        return artists;
    }

    public void addArtist(String name, String genre) {
        artistRepository.save(new Artist(genre, name));
        System.out.println("Artist added");
    }

    public void findByGenre(String genre) {
        List<Artist> artists = artistRepository.findByGenre(genre);
        if (artists.isEmpty()) {
            System.out.println("No artists for: " + genre);
            return;
        }
        for (Artist a : artists) {
            System.out.println(a.getName() + " - " + a.getGenre());
        }
    }

    public void findByName(String name) {
        List<Artist> artists = artistRepository.findByNameContainingIgnoreCase(name);
        if (artists.isEmpty()) {
            System.out.println("No artists named: " + name);
            return;
        }
        for (Artist a : artists) {
            System.out.println(a.getName() + " - " + a.getGenre());
        }
    }

    public void updateGenre(Artist artist, String newGenre) {
        artist.setGenre(newGenre);
        artistRepository.save(artist);
        System.out.println("Genre updated");
    }

    public void deleteArtist(Artist artist) {
        artistRepository.delete(artist);
        System.out.println("Artist deleted");
    }
}



