package edu.dexequiel.msscbeerservice.service;

import edu.dexequiel.msscbeerservice.domain.Beer;
import edu.dexequiel.msscbeerservice.exception.NotFoundException;
import edu.dexequiel.msscbeerservice.mapper.BeerMapper;
import edu.dexequiel.msscbeerservice.repositories.BeerRepository;
import edu.dexequiel.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public class BeerServiceImpl implements BeerService {

    private BeerRepository repository;
    private BeerMapper mapper;

    @Override
    public BeerDto getById(UUID uuid) {
        return mapper.asBeerDto(repository.findById(uuid).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return mapper.asBeerDto(repository.save(mapper.asBeer(beerDto)));
    }

    @Override
    public BeerDto update(UUID uuid, BeerDto beerDto) {
        Beer beer = repository.findById(uuid).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return mapper.asBeerDto(repository.save(beer));
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }
}
