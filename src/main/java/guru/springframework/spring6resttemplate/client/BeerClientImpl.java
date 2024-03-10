package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
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

import java.util.Map;

@Service
@AllArgsConstructor
public class BeerClientImpl implements BeerClient {
//    public static final String BASE_URL="http://localhost:8080";
    public static final String GET_BEER_PATH="/api/v1/beer";

    private final RestTemplateBuilder restTemplateBuilder;

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
