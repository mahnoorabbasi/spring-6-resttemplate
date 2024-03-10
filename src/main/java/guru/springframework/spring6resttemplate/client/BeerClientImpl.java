package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class BeerClientImpl implements BeerClient {
//    public static final String BASE_URL="http://localhost:8080";
    public static final String GET_BEER_PATH="/api/v1/beer";

    private final RestTemplateBuilder restTemplateBuilder;
    @Override
    public Page<BeerDTO> listBeers() {

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

        ResponseEntity<BeerDTOPageImpl> beerDtoResponseEntity=restTemplateBuilder.build()
                .getForEntity(/*BASE_URL+*/GET_BEER_PATH, BeerDTOPageImpl.class);


        System.out.println(beerDtoResponseEntity.getBody());
        return null;
    }
}
