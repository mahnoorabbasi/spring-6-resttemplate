package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void testBeerClientGet() {

        beerClient.listBeers(null,null,false,null,null);

    }
    @Test
    void testBeerClientQueryParams() {

        beerClient.listBeers("ALE", BeerStyle.LAGER,true,1,25);

    }
}