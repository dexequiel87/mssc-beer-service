package edu.dexequiel.msscbeerservice.bootstrap;

import edu.dexequiel.msscbeerservice.domain.Beer;
import edu.dexequiel.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private BeerRepository repository;

    public BeerLoader(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (repository.count() == 0) {
            repository.save(Beer.builder()
                    .beerName("Frangus IPA")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(337010000001L)
                    .price(new BigDecimal(12.95))
                    .build());


            repository.save(Beer.builder()
                    .beerName("Holmes Stout")
                    .beerStyle("Stout")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(337010000002L)
                    .price(new BigDecimal(12.95))
                    .build());
        }

    }
}
