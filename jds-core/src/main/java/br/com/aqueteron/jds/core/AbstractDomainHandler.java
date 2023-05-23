package br.com.aqueteron.jds.core;

import br.com.aqueteron.jds.core.api.DomainApiSchema;
import br.com.aqueteron.jds.core.api.DomainValueApiSchema;

import java.util.List;

public class AbstractDomainHandler {

    DomainApiSchema loadDomainApiSchema(final DomainServiceMap<? extends Enum<?>> domainServiceMap) {
        return DomainApiSchema.builder()
                .name(domainServiceMap.getResource())
                .values(loadApiValuesList(domainServiceMap)).build();
    }

    private List<DomainValueApiSchema> loadApiValuesList(final DomainServiceMap<? extends Enum<?>> domainServiceMap) {
        return domainServiceMap.getDomainValues().stream()
                .map(value -> this.toApiValue(value, domainServiceMap))
                .toList();
    }

    private DomainValueApiSchema toApiValue(final Enum<? extends Enum<?>> value, final DomainServiceMap<? extends Enum<?>> domainServiceMap) {
        return DomainValueApiSchema
                .builder()
                .id(domainServiceMap.getId(value))
                .key(domainServiceMap.getKey(value))
                .build();
    }
}
