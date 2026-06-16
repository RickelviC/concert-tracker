package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.seeder.DataSeed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUpRunner implements CommandLineRunner {

    private final DataSeed dataSeed;

    public StartUpRunner(DataSeed dataSeed) {
        this.dataSeed = dataSeed;
    }

    @Override
    public void run(String... args) {
        dataSeed.loadData();
    }
}