package br.com.aqueteron.jds.integration.test;

import br.com.aqueteron.jds.core.SimpleDomain;

@SimpleDomain(idMethod = "getId")
public enum EnumStatusCustomId {

    OPENED(10), CLOSED(90), IN_PROGRESS(20);

    private final Integer id;

    private EnumStatusCustomId(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
