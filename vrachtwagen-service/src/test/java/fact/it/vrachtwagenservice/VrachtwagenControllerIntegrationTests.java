package fact.it.vrachtwagenservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.vrachtwagenservice.model.Vrachtwagen;
import fact.it.vrachtwagenservice.repository.VrachtwagenRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class VrachtwagenControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VrachtwagenRepository vrachtwagenRepository;

    private Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "1-IUY-234", "Ordina");
    private Vrachtwagen v2 = new Vrachtwagen("2", "Renault", "Vrachtwagen", "2020", "1-RTY-555", "Ordina");
    private Vrachtwagen v3 = new Vrachtwagen("3", "DAF", "Betere vrachtwagen", "2018", "1-REZ-345", "Thomas More");
    private Vrachtwagen vDelete = new Vrachtwagen("4", "Test", "test", "2020", "test", "test");

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void beforeAllTests() {
        vrachtwagenRepository.deleteAll();
        vrachtwagenRepository.save(v1);
        vrachtwagenRepository.save(v2);
        vrachtwagenRepository.save(v3);
        vrachtwagenRepository.save(vDelete);
    }

    @AfterEach
    public void afterAllTests() {
        vrachtwagenRepository.deleteAll();
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByBedrijf_thenReturnJsonVrachtwagens() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);

        mockMvc.perform(get("/vrachtwagens/{bedrijf}", "Ordina"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].merk", is("Volvo")))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2021")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-IUY-234")))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")));
    }


    @Test
    public void whenPostVrachtwagen_thenReturnJsonVrachtwagen() throws Exception {
        Vrachtwagen vNew = new Vrachtwagen("5", "Saab", "Vrachtwagen", "2009", "3-OPE-345", "SAP Enterprises");

        mockMvc.perform(post("/vrachtwagens")
                .content(mapper.writeValueAsString(vNew))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("5")))
                .andExpect(jsonPath("$.merk", is("Saab")))
                .andExpect(jsonPath("$.model", is("Vrachtwagen")))
                .andExpect(jsonPath("$.bouwjaar", is("2009")))
                .andExpect(jsonPath("$.nummerplaat", is("3-OPE-345")))
                .andExpect(jsonPath("$.bedrijf", is("SAP Enterprises")));
    }

    @Test
    public void givenVrachtwagen_whenDeleteVrachtwagen_thenStatusOk() throws Exception {
        mockMvc.perform(delete("/vrachtwagens/{id}", "4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void givenNoVrachtwagen_whenDeleteVrachtwagen_thenStatusNotFound() throws Exception {
        mockMvc.perform(delete("/vrachtwagens/{id}", "9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }






}
