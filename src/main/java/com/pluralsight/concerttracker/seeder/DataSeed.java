package com.pluralsight.concerttracker.seeder;

import com.pluralsight.concerttracker.data.*;
import com.pluralsight.concerttracker.models.*;

import org.springframework.stereotype.Service;

@Service
public class DataSeed {

    private ArtistRepository artistRepository;
    private ConcertRepository concertRepository;
    private PromoterRepository promoterRepository;
    private VenueRepository venueRepository;

    public DataSeed(ArtistRepository artistRepository, ConcertRepository concertRepository, PromoterRepository promoterRepository, VenueRepository venueRepository) {
        this.artistRepository = artistRepository;
        this.concertRepository = concertRepository;
        this.promoterRepository = promoterRepository;
        this.venueRepository = venueRepository;
    }

    public void loadData() {
        if (concertRepository.count() == 0 && artistRepository.count() == 0 &&
                promoterRepository.count() == 0 && venueRepository.count() == 0) {
            seedArtist();
        }
    }

    private void seedArtist() {
        Artist kendrickLamar = artistRepository.save(new Artist("Rap/Hip-Hop", "Kendrick Lamar"));
        Artist badBunny = artistRepository.save(new Artist("Latin pop", "Bad Bunny"));
        Artist drake = artistRepository.save(new Artist("Hip-Hop", "Drake"));
        Artist michaelJackson = artistRepository.save(new Artist("Pop/R&B", "Michael Jackson"));
        Artist brunoMars = artistRepository.save(new Artist("Pop/R&B ", "Bruno Mars"));

        Promoter liveNationEntertainment = promoterRepository.save(new Promoter("Live Nation Entertainment"));
        Promoter aegPresents = promoterRepository.save(new Promoter("Aeg Presents"));
        Promoter ocesa = promoterRepository.save(new Promoter("Ocesa"));
        Promoter cardenasMarketingNetwork = promoterRepository.save(new Promoter("Cardenas Marketing Network"));

        Venue madisonSquareGarden = venueRepository.save(new Venue("Madison Square Garden", "New York City", 20000L));
        Venue sphere = venueRepository.save(new Venue("Sphere", "Las Vegas", 17600L));
        Venue kiaForum = venueRepository.save(new Venue("Kia Forum", "Inglewood", 17500L));
        Venue metLifeStadium = venueRepository.save(new Venue("MetLife Stadium", "East Rutherford", 82500L));

        concertRepository.save(new Concert(2026, 199.99, 18500, kendrickLamar, madisonSquareGarden,
                liveNationEntertainment));

        concertRepository.save(new Concert(2026, 249.99, 72000, badBunny, metLifeStadium, aegPresents));

        concertRepository.save(new Concert(2026, 179.99, 15000, drake, sphere, ocesa));

        concertRepository.save(new Concert(2026, 299.99, 80000, michaelJackson, metLifeStadium,
                cardenasMarketingNetwork));

        concertRepository.save(new Concert(2026, 149.99, 16500, brunoMars, kiaForum, aegPresents));

        concertRepository.save(new Concert(2025, 219.99, 17000, kendrickLamar, kiaForum,
                liveNationEntertainment));

        concertRepository.save(new Concert(2025, 189.99, 14000, badBunny, sphere, ocesa));

        concertRepository.save(new Concert(2025, 129.99, 19000, brunoMars, madisonSquareGarden,
                cardenasMarketingNetwork));
    }
}

