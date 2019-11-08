package edu.dexequiel.msscbeerservice.service;

import edu.dexequiel.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID uuid);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto update(UUID uuid, BeerDto beerDto);

    void delete(UUID uuid);
}
