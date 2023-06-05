package br.com.aqueteron.jds.core;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.webmvc.core.fn.SpringdocRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.webmvc.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class JdsRoutingConfiguration {

    private final ByDomainHandler byDomainHandler;

    private final GenericDomainHandler genericDomainHandler;

    private final DomainServiceMapProvider domainServiceMapProvider;

    @Autowired
    public JdsRoutingConfiguration(final ByDomainHandler byDomainHandler, final GenericDomainHandler genericDomainHandler, final DomainServiceMapProvider domainServiceMapProvider) {
        this.byDomainHandler = byDomainHandler;
        this.genericDomainHandler = genericDomainHandler;
        this.domainServiceMapProvider = domainServiceMapProvider;
    }

    @Bean
    @ConditionalOnProperty(name = "jds.route.strategy", havingValue = "by_domain", matchIfMissing = false)
    public RouterFunction<ServerResponse> loadRoutesByDomainStrategy(@Value("${jds.route.domain-prefix-url:}") final String domainPrefixUrl) {
        log.info("Loading JDS routes with: by domain");
        log.debug("Domain prefix URL with: {}", domainPrefixUrl);
        SpringdocRouteBuilder springdocRouteBuilder = route();
        for (DomainServiceMap<?> domainServiceMap : this.domainServiceMapProvider.loadDomainServiceMap()) {
            springdocRouteBuilder.GET(
                    domainPrefixUrl + "/" + domainServiceMap.getResource(),
                    accept(MediaType.APPLICATION_JSON),
                    sr -> this.byDomainHandler.loadDomainValues(sr, domainServiceMap),
                    ops -> this.byDomainHandler.loadDomainDocumentation(ops,domainServiceMap)
            );
        }
        return springdocRouteBuilder.build();
    }

    @Bean
    @ConditionalOnProperty(name = "jds.route.strategy", havingValue = "generic", matchIfMissing = true)
    public RouterFunction<ServerResponse> loadRoutesByGenericStrategy(@Value("${jds.route.domain-prefix-url:}") final String domainPrefixUrl) {
        log.info("Loading JDS routes with: generic endpoint");
        log.debug("Domain prefix URL with: {}", domainPrefixUrl);
        return route()
                .GET(domainPrefixUrl + "/domains", accept(MediaType.APPLICATION_JSON), this.genericDomainHandler::getDefaultDomains, ops -> ops
                        .operationId("getDomains")
                        .summary("Get domains")
                        .tags(new String[]{"domains"})
                        .description("Retrieve a domain list")
                        .response(responseBuilder().responseCode("200").description("Success to retrieve domains."))
                        .build())
                .GET(domainPrefixUrl + "/domains/{key}", accept(MediaType.APPLICATION_JSON), this.genericDomainHandler::getDefaultDomain, ops -> ops
                        .operationId("getDomains")
                        .summary("Get domains")
                        .tags(new String[]{"domains"})
                        .description("Retrieve a domain values")
                        .parameter(parameterBuilder().name("key").required(true).in(ParameterIn.PATH))
                        .response(responseBuilder().responseCode("200").description("Success to retrieve domain values."))
                        .build())
                .build();
    }
}
