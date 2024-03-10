package guru.springframework.spring6resttemplate.config;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfigBuilder {
    @Value("${rest.template.rootUrl}")
    String rootUrl ;

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){
        RestTemplateBuilder restTemplateBuilder=configurer.configure(new RestTemplateBuilder());
        DefaultUriBuilderFactory defaultUriBuilderFactory=
                new DefaultUriBuilderFactory(rootUrl);
        return restTemplateBuilder.uriTemplateHandler(defaultUriBuilderFactory);

    }
}

