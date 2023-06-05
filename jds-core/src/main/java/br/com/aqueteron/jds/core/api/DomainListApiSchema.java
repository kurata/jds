package br.com.aqueteron.jds.core.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DomainListApiSchema {

    private final List<String> domains;

}
