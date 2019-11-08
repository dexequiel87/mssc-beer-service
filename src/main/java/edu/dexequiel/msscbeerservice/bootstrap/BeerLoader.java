package edu.dexequiel.msscbeerservice.bootstrap;

import edu.dexequiel.msscbeerservice.domain.Beer;
import edu.dexequiel.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private static String BEER_1_UPC = "065100004327";
    private static String BEER_2_UPC = "065100004328";
    private static String BEER_3_UPC = "065100004329";

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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal(12.95))
                    .build());

            repository.save(Beer.builder()
                    .beerName("Holmes Stout")
                    .beerStyle("Stout")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal(12.95))
                    .build());

            repository.save(Beer.builder()
                    .beerName("Andes Origen")
                    .beerStyle("Red Lager")
                    .quantityToBrew(300)
                    .minOnHand(15)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal(6.95))
                    .build());
        }

    }
}
