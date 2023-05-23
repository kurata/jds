package br.com.aqueteron.jds.integration.test;

import br.com.aqueteron.jds.integration.test.utils.AbstractApplicationTestStart;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test-by-domain")
class ByDomainApplicationIntegrationTest extends AbstractApplicationTestStart {

    @Test
    void contextLoad() {

    }

}
