package br.com.aqueteron.jds.integration.test;

import br.com.aqueteron.jds.integration.test.utils.AbstractApplicationTestStart;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-default")
class GenericDomainIT extends AbstractApplicationTestStart {

    @Test
    void shouldLoadDomains() throws Exception {
        mockMvc()
                .perform(get("/domains"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"domains\":[\"enum-status-custom-key\",\"enum-status-default-type\",\"enum-status-custom-id\",\"custom-path-status\"]}"));
    }

    @Test
    void shouldLoadDomainValues() throws Exception {
        mockMvc()
                .perform(get("/domains/enum-status-default-type"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"enum-status-default-type\",\"values\":[{\"id\":0,\"key\":\"OPENED\"},{\"id\":1,\"key\":\"CLOSED\"},{\"id\":2,\"key\":\"IN_PROGRESS\"}]}"));

        mockMvc()
                .perform(get("/domains/enum-status-custom-key"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"enum-status-custom-key\",\"values\":[{\"id\":0,\"key\":\"opened_custom_key\"},{\"id\":1,\"key\":\"closed_custom_key\"},{\"id\":2,\"key\":\"in_progress_custom_key\"}]}"));

        mockMvc()
                .perform(get("/domains/enum-status-custom-id"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"enum-status-custom-id\",\"values\":[{\"id\":10,\"key\":\"OPENED\"},{\"id\":90,\"key\":\"CLOSED\"},{\"id\":20,\"key\":\"IN_PROGRESS\"}]}"));

        mockMvc()
                .perform(get("/domains/custom-path-status"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"custom-path-status\",\"values\":[{\"id\":0,\"key\":\"OPENED\"},{\"id\":1,\"key\":\"CLOSED\"},{\"id\":2,\"key\":\"IN_PROGRESS\"}]}"));
    }

}
