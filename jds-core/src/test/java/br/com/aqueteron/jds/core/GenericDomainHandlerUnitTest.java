package br.com.aqueteron.jds.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenericDomainHandlerUnitTest {

    @Mock
    private DomainServiceMapProvider domainServiceMapProviderMock;

    @InjectMocks
    private GenericDomainHandler genericDomainHandler;

    @Test
    void shouldGetDefaultDomainsWithEmptyArray() {
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(this.domainServiceMapProviderMock.loadDomainServiceMap()).thenReturn(new ArrayList<>());
        ServerResponse serverResponse = this.genericDomainHandler.getDefaultDomains(serverRequest);
        assertEquals(HttpStatusCode.valueOf(200), serverResponse.statusCode());
    }

    @Test
    void shouldGetDefaultDomainsWithSomeDomains() {
        List<DomainServiceMap<? extends Enum<?>>> domainServiceMapList = new ArrayList<>(1);
        domainServiceMapList.add(
                new DomainServiceMap<>(EnumStatusDefaultType.class, "enum-status-default-type", EnumStatusDefaultType::ordinal, EnumStatusDefaultType::name)
        );
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(this.domainServiceMapProviderMock.loadDomainServiceMap()).thenReturn(domainServiceMapList);
        ServerResponse serverResponse = this.genericDomainHandler.getDefaultDomains(serverRequest);
        assertEquals(HttpStatusCode.valueOf(200), serverResponse.statusCode());
    }

    @Test
    void shouldGetDefaultDomain() {
        List<DomainServiceMap<? extends Enum<?>>> domainServiceMapList = new ArrayList<>(1);
        domainServiceMapList.add(
                new DomainServiceMap<>(EnumStatusDefaultType.class, "enum-status-default-type", EnumStatusDefaultType::ordinal, EnumStatusDefaultType::name)
        );
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(serverRequest.pathVariable("key")).thenReturn("enum-status-default-type");
        when(this.domainServiceMapProviderMock.loadDomainServiceMap()).thenReturn(domainServiceMapList);
        ServerResponse serverResponse = this.genericDomainHandler.getDefaultDomain(serverRequest);
        assertEquals(HttpStatusCode.valueOf(200), serverResponse.statusCode());
    }

    @Test
    void shouldGetDefaultDomainNotFound() {
        List<DomainServiceMap<? extends Enum<?>>> domainServiceMapList = new ArrayList<>(1);
        domainServiceMapList.add(
                new DomainServiceMap<>(EnumStatusDefaultType.class, "enum-status-default-type", EnumStatusDefaultType::ordinal, EnumStatusDefaultType::name)
        );
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(serverRequest.pathVariable("key")).thenReturn("wrong-resource");
        when(this.domainServiceMapProviderMock.loadDomainServiceMap()).thenReturn(domainServiceMapList);
        assertThrows(JdsDomainNotFoundException.class, () -> this.genericDomainHandler.getDefaultDomain(serverRequest));
    }

}
