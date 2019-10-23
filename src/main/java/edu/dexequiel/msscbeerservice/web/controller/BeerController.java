package edu.dexequiel.msscbeerservice.web.controller;

import edu.dexequiel.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    @GetMapping("/{uuid}")
    public ResponseEntity<BeerDto> getById(@PathVariable("uuid") UUID uuid) {
        // TODO impl
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated BeerDto beerDto) {
        // TODO impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public  ResponseEntity update(@PathVariable("uuid") UUID uuid, @RequestBody @Validated BeerDto beerDto) {
        // TODO impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public  ResponseEntity delete(@PathVariable("uuid") UUID uuid) {
        // TODO impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
