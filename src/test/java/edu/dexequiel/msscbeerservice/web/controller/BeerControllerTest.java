package edu.dexequiel.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.dexequiel.msscbeerservice.web.model.BeerDto;
import edu.dexequiel.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getById() throws Exception {

        mockMvc.perform(get("/api/v1/beers/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {

        BeerDto beerDto = BeerDto.builder()
                .beerName("Galaxy Ale")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(20))
                .upc(5)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(status().isCreated());
    }

    @Test
    void badRequestCreate() throws Exception {

        BeerDto beerDto = BeerDto.builder()
                .beerName("Galaxy Ale")
                .id(UUID.randomUUID())
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(20))
                .upc(5)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void badRequestUpdate() throws Exception {

        BeerDto beerDto = BeerDto.builder()
                .upc(-5)
                .beerName("Galaxy Ale")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(20))
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beers/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {

        BeerDto beerDto = BeerDto.builder()
                .beerName("Galaxy Ale")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(20))
                .upc(5)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beers/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testHandleDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/beers/" + UUID.randomUUID().toString())
        ).andExpect(status().isNoContent());
    }
}