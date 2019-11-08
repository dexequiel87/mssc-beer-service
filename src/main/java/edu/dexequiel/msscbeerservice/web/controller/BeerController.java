package edu.dexequiel.msscbeerservice.web.controller;

import edu.dexequiel.msscbeerservice.service.BeerService;
import edu.dexequiel.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private BeerService beerService;

    @GetMapping("/{uuid}")
    public ResponseEntity<BeerDto> getById(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(beerService.getById(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public  ResponseEntity update(@PathVariable("uuid") UUID uuid, @RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity(beerService.update(uuid, beerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public  ResponseEntity delete(@PathVariable("uuid") UUID uuid) {
        beerService.delete(uuid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
