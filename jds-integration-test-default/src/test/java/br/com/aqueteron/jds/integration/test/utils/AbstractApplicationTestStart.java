package br.com.aqueteron.jds.integration.test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SqlConfig(encoding = "UTF-8")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AbstractApplicationTestStart {

    @LocalServerPort
    private Integer port;

    @Autowired
    private MockMvc mockMvc;

    public int port() {
        return this.port;
    }

    protected MockMvc mockMvc() {
        return this.mockMvc;
    }

}
