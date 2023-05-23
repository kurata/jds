package br.com.aqueteron.jds.integration.test;


import br.com.aqueteron.jds.core.SimpleDomain;

@SimpleDomain(keyMethod = "getKey")
public enum EnumStatusCustomKey {

    OPENED("opened_custom_key"), CLOSED("closed_custom_key"), IN_PROGRESS("in_progress_custom_key");

    private final String key;

    private EnumStatusCustomKey(final String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
