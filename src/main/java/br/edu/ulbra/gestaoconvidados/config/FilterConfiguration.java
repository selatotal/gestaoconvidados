package br.edu.ulbra.gestaoconvidados.config;

import br.edu.ulbra.gestaoconvidados.components.URLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.sasl.AuthenticationException;

/**
 * Created by talesviegas on 27/06/17.
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean authenticatedFilter() throws AuthenticationException {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new URLFilter());
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("AuthenticatedFilter");
        registration.setOrder(1);
        return registration;
    }

}
