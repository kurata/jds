package br.com.aqueteron.jds.core;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;

@Slf4j
@Component
public class ByDomainHandler extends AbstractDomainHandler {

    public ServerResponse loadDomainValues(final ServerRequest serverRequest, final DomainServiceMap<? extends Enum<?>> domainServiceMap) {
        return ServerResponse.ok().body(loadDomainApiSchema(domainServiceMap));
    }

    public void loadDomainDocumentation(Builder builder, DomainServiceMap<? extends Enum<?>> domainServiceMap) {
        builder
                .operationId("getDomains")
                .summary("Get domains")
                .tags(new String[]{"domains"})
                .description("Retrieve a domain list")
                .response(responseBuilder().responseCode("200").description("Success to retrieve domains."))
                .build();
    }
}
