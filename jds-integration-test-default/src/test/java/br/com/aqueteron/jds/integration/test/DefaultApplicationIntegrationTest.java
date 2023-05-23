package br.com.aqueteron.jds.integration.test;

import br.com.aqueteron.jds.integration.test.utils.AbstractApplicationTestStart;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test-default")
class DefaultApplicationIntegrationTest extends AbstractApplicationTestStart {

    @Test
    void contextLoad() {

    }

}
