package br.com.aqueteron.jds.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteStrategyTest {

    @Test
    void routeStrategyValues() {
        assertEquals(0, RouteStrategy.GENERIC.ordinal());
        assertEquals("GENERIC", RouteStrategy.GENERIC.name());
        assertEquals(1, RouteStrategy.BY_DOMAIN.ordinal());
        assertEquals("BY_DOMAIN", RouteStrategy.BY_DOMAIN.name());
    }

}
