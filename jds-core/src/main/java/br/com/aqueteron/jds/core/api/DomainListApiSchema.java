package br.com.aqueteron.jds.core.api;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
public class DomainListApiSchema {

    private final List<String> domains;

}
