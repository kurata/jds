package br.com.aqueteron.jds.core.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DomainApiSchema {

    private final String name;

    private final List<DomainValueApiSchema> values;

}
