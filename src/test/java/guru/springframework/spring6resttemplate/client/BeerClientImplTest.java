package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void testDeletBeerById() {
        BeerDTO beerDTO=BeerDTO.builder()
                .beerName("TestName")
                .beerStyle(BeerStyle.LAGER)
                .upc("1244")
                .price(new BigDecimal("12"))
                .quantityOnHand(433)
                .build();

        BeerDTO createdBeer=beerClient.createBeer(beerDTO);

        beerClient.deleteBeerById(createdBeer.getId());
        assertThrows(HttpClientErrorException.class, ()->
                beerClient.getBeerById(createdBeer.getId())
                );

    }

    @Test
    void testUpdateBeerById() {
        BeerDTO beerDTO=BeerDTO.builder()
                .beerName("TestName")
                .beerStyle(BeerStyle.LAGER)
                .upc("1244")
                .price(new BigDecimal("12"))
                .quantityOnHand(433)
                .build();

        BeerDTO createdBeer=beerClient.createBeer(beerDTO);

        final String beerName="Test name Updated";
        beerDTO.setBeerName(beerName);
        BeerDTO updatedBeer=beerClient.updateBeerById(createdBeer.getId(), beerDTO);

//        BeerDTO updatedBeer=beerClient.getBeerById(createdBeer.getId());
        assertEquals(beerName, updatedBeer.getBeerName());
    }

    @Test
    void testCreateBeer() {
        BeerDTO beerDTO=BeerDTO.builder()
                .beerName("TestName")
                .beerStyle(BeerStyle.LAGER)
                .upc("1244")
                .price(new BigDecimal("12"))
                .quantityOnHand(433)
                .build();

        BeerDTO createdBeer=beerClient.createBeer(beerDTO);
        assertNotNull(createdBeer);
    }

    @Test
    void testGetBeerById() {
        List<BeerDTO> beers=beerClient.listBeers().getContent();
        BeerDTO beerDTO=beers.get(0);

        BeerDTO obtainedBeer=beerClient.getBeerById(beerDTO.getId());
        assert obtainedBeer!=null;


    }

    @Test
    void testBeerClientGet() {

        beerClient.listBeers(null,null,false,null,null);

    }
    @Test
    void testBeerClientQueryParams() {

        beerClient.listBeers("ALE", BeerStyle.LAGER,true,1,25);

    }
}