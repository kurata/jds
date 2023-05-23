package br.com.aqueteron.jds.core;

import java.util.List;

public interface DomainServiceMapProvider {

    List<DomainServiceMap<? extends Enum<?>>> loadDomainServiceMap();

}
