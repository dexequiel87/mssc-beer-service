package edu.dexequiel.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.dexequiel.msscbeerservice.service.BeerService;
import edu.dexequiel.msscbeerservice.web.model.BeerDto;
import edu.dexequiel.msscbeerservice.web.model.BeerStyleEnum;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @BeforeEach
    void before() {

    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Test beer")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(12.90))
                .upc("065205661132")
                .quantityOnHand(20)
                .build();
    }

    @Test
    void getById() throws Exception {

        given(beerService.getById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beers/{beerId}", UUID.randomUUID().toString())
                .param("pageSize", "20")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer/",
                        pathParameters(
                                parameterWithName("beerId").description("UUID of the desired beer to get")
                        ),
                        requestParameters(
                                parameterWithName("pageSize").description("The page size")
                        )
                        ));
    }


    @Test
    void create() throws Exception {

        BeerDto beerDto = BeerDto.builder()
                .beerName("Galaxy Ale")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(20))
                .upc("065100004001")
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

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
                .upc("065100004002")
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void badRequestUpdate() throws Exception {

        BeerDto beerDto = BeerDto.builder()
                .upc("065100004003")
                .beerName("Galaxy Ale")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(-20))
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.update(any(), any())).willReturn(getValidBeerDto());

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
                .upc("065100004004")
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.update(any(), any())).willReturn(getValidBeerDto());

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