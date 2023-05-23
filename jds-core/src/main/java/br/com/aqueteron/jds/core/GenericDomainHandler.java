package br.com.aqueteron.jds.core;

import br.com.aqueteron.jds.core.api.DomainListApiSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

@Slf4j
@Component
public class GenericDomainHandler extends AbstractDomainHandler {

    private final DomainServiceMapProvider domainServiceMapProvider;

    @Autowired
    public GenericDomainHandler(final DomainServiceMapProvider domainServiceMapProvider) {
        this.domainServiceMapProvider = domainServiceMapProvider;
    }

    public ServerResponse getDefaultDomains(final ServerRequest serverRequest) {
        List<DomainServiceMap<? extends Enum<?>>> domainServiceMapList = this.domainServiceMapProvider.loadDomainServiceMap();
        return ServerResponse.ok().body(new DomainListApiSchema(domainServiceMapList.stream().map(DomainServiceMap::getResource).toList()));
    }

    public ServerResponse getDefaultDomain(final ServerRequest serverRequest) {
        List<DomainServiceMap<? extends Enum<?>>> domainServiceMapList = this.domainServiceMapProvider.loadDomainServiceMap();
        return ServerResponse.ok().body(domainServiceMapList.stream().filter(dsm -> dsm.getResource().equals(serverRequest.pathVariable("key"))).findFirst().map(this::loadDomainApiSchema));
    }

}
