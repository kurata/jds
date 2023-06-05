package br.com.aqueteron.jds.core.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class DomainValueApiSchema implements Serializable {

    private final Integer id;

    private final String key;
}
