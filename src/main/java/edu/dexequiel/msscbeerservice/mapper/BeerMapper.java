package edu.dexequiel.msscbeerservice.mapper;

import edu.dexequiel.msscbeerservice.domain.Beer;
import edu.dexequiel.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    Beer asBeer(BeerDto beerDto);

    BeerDto asBeerDto(Beer beer);
}
