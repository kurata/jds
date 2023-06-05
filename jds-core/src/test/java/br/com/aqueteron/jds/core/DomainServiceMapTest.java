package br.com.aqueteron.jds.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainServiceMapTest {

    @Test
    void validateRetrieveMethods() {
        DomainServiceMap<EnumStatusDefaultType> domainServiceMap = new DomainServiceMap<>(EnumStatusDefaultType.class,
                "resource", EnumStatusDefaultType::ordinal, EnumStatusDefaultType::name);
        assertEquals(Arrays.asList(EnumStatusDefaultType.values()), domainServiceMap.getDomainValues());
        assertEquals(0, domainServiceMap.getId(EnumStatusDefaultType.OPENED));
        assertEquals("OPENED", domainServiceMap.getKey(EnumStatusDefaultType.OPENED));
        assertEquals(EnumStatusDefaultType.class, domainServiceMap.getEnumClass());
        assertEquals("resource", domainServiceMap.getResource());
        domainServiceMap.getGetIdFunction();
        domainServiceMap.getGetKeyFunction();
    }

}
