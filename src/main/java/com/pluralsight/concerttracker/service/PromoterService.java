package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.models.Promoter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoterService {

    private final PromoterRepository promoterRepository;

    public PromoterService(PromoterRepository promoterRepository) {
        this.promoterRepository = promoterRepository;
    }

    public List<Promoter> listAll() {
        List<Promoter> promoters = promoterRepository.findAll();
        if (promoters.isEmpty()) {
            System.out.println("No promoters found.");
            return promoters;
        }
        for (Promoter promoter : promoters) {
            System.out.println((promoters.indexOf(promoter) + 1) + ") " + promoter.getName());
        }
        return promoters;
    }

    public void addPromoter(String name) {
        promoterRepository.save(new Promoter(name));
        System.out.println("Promoter added");
    }

    public void findByName(String name) {
        List<Promoter> promoters = promoterRepository.findByNameContainingIgnoreCase(name);
        if (promoters.isEmpty()) {
            System.out.println("No promoters named: " + name);
            return;
        }
        for (Promoter promoter : promoters) {
            System.out.println(promoter.getName());
        }
    }

    public void deletePromoter(Promoter promoter) {
        promoterRepository.delete(promoter);
        System.out.println("Promoter deleted");
    }
}