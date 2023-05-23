package br.com.aqueteron.jds.core.api;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@RequiredArgsConstructor
public class DomainValueApiSchema implements Serializable {

    private final Integer id;

    private final String key;
}
