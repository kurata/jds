package br.com.aqueteron.jds.core.api;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
public class DomainApiSchema {

    private final String name;

    private final List<DomainValueApiSchema> values;

}
