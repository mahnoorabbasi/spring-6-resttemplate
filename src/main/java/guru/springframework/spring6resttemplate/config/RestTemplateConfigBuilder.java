package guru.springframework.spring6resttemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.util.DefaultUriBuilderFactory;


/*public class RestTemplateConfigBuilder {
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

}*/
@Configuration
public class RestTemplateConfigBuilder {
    @Value("${rest.template.rootUrl}")
    String rootUrl;

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public RestTemplateConfigBuilder(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }



    @Bean
    OAuth2AuthorizedClientManager auth2AuthorizedClientManager(){
        var authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();

        var authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager
                (clientRegistrationRepository, oAuth2AuthorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer, OAuthClientInterceptor oAuthClientInterceptor){

        assert rootUrl != null;

        return configurer.configure(new RestTemplateBuilder())
                .additionalInterceptors(oAuthClientInterceptor)
                .uriTemplateHandler(new DefaultUriBuilderFactory(rootUrl));
    }
}

