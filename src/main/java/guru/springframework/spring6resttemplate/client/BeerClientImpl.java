package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    //    public static final String BASE_URL="http://localhost:8080";
    public static final String GET_BEER_PATH="/api/v1/beer";
    public static final String GET_BEER_BY_ID="/api/v1/beer/{beerId}";

    @Override
    public void deleteBeerById(UUID beerId) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.delete(GET_BEER_BY_ID,beerId );

    }

    @Override
    public BeerDTO updateBeerById(UUID beerId, BeerDTO beerDTO) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.put(GET_BEER_BY_ID,beerDTO,beerId );

        return getBeerById(beerId);


    }

    @Override
    public BeerDTO createBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<BeerDTO> response=restTemplate.postForEntity(GET_BEER_PATH,beerDTO, BeerDTO.class); //used to return if we are sending beerdto type response

        URI uri=restTemplate.postForLocation(GET_BEER_PATH,beerDTO);
        return restTemplate.getForObject(uri.getPath(),BeerDTO.class);
    }


    @Override
    public BeerDTO getBeerById(UUID beerId) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        return restTemplate.getForObject(GET_BEER_BY_ID, BeerDTO.class, beerId);
    }

    @Override
    public Page<BeerDTO> listBeers() {
        return listBeers(null,null,null,null,null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName) {
        return listBeers(beerName,null,null,null,null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle) {
        return listBeers(beerName,beerStyle,null,null,null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory) {
        return listBeers(beerName,beerStyle,showInventory,null,null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize) {

        RestTemplate restTemplate=restTemplateBuilder.build();
        UriComponentsBuilder uriComponentsBuilder=UriComponentsBuilder.fromPath(GET_BEER_PATH);
        if(beerName!=null){
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        if(beerStyle!=null){
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }
        if(showInventory!=null){
            uriComponentsBuilder.queryParam("showInventory", showInventory);
        }
        if(pageNumber!=null){
            uriComponentsBuilder.queryParam("pageNumber", pageNumber);
        }
        if(pageSize!=null){
            uriComponentsBuilder.queryParam("beerStyle", pageSize);
        }


//        ResponseEntity<String> stringResponse=restTemplateBuilder.build().getForEntity(BASE_URL+GET_BEER_PATH, String.class);
//
//        ResponseEntity<Map> mapResponseEntity=restTemplateBuilder.build().getForEntity(BASE_URL+GET_BEER_PATH, Map.class);
//
//        ResponseEntity<JsonNode> jsonResponseEntity=restTemplateBuilder.build()
//                .getForEntity(BASE_URL+GET_BEER_PATH, JsonNode.class);
//        jsonResponseEntity.getBody().findPath("content")
//                .elements()
//                        .forEachRemaining(node->{
//                            System.out.println(node.get("beerName").asText());
//                        });

        ResponseEntity<BeerDTOPageImpl> beerDtoResponseEntity=restTemplate
                .getForEntity(/*BASE_URL+*/ /*GET_BEER_PATH*/uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);


        System.out.println(beerDtoResponseEntity.getBody());
        return beerDtoResponseEntity.getBody();
    }
}
