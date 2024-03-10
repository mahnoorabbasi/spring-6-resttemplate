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
    @Value("${rest.template.username}")
    String username ;
    @Value("${rest.template.password}")
    String password ;

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){
//        RestTemplateBuilder restTemplateBuilder=configurer.configure(new RestTemplateBuilder());
//        DefaultUriBuilderFactory defaultUriBuilderFactory=
//                new DefaultUriBuilderFactory(rootUrl);
//        RestTemplateBuilder secBuilder=restTemplateBuilder.basicAuthentication(username,password);
//        return secBuilder.uriTemplateHandler(defaultUriBuilderFactory);
        assert rootUrl != null;

        return configurer.configure(new RestTemplateBuilder())
                .basicAuthentication(username, password)
                .uriTemplateHandler(new DefaultUriBuilderFactory(rootUrl));
    }
}

