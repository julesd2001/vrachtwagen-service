package fact.it.vrachtwagenservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.vrachtwagenservice.model.Vrachtwagen;
import fact.it.vrachtwagenservice.repository.VrachtwagenRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    private Vrachtwagen vDelete = new Vrachtwagen("4", "Test", "test", "2020", "1-ARE-788", "test");
    private Vrachtwagen v4 = new Vrachtwagen("5", "Renault", "Andere Vrachtwagen", "2020", "1-RGH-444", "The Value Chain");

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void beforeAllTests() {
        vrachtwagenRepository.deleteAll();
        vrachtwagenRepository.save(v1);
        vrachtwagenRepository.save(v2);
        vrachtwagenRepository.save(v3);
        vrachtwagenRepository.save(v4);
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
    public void givenVrachtwagen_whenGetVrachtwagenByNummerplaat_thenReturnJsonVrachtwagen() throws Exception {
        mockMvc.perform(get("/vrachtwagens/{nummerplaat}", "1-IUY-234"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merk", is("Volvo")))
                .andExpect(jsonPath("$.bedrijf", is("Ordina")))
                .andExpect(jsonPath("$.bouwjaar", is("2021")))
                .andExpect(jsonPath("$.model", is("Vrachtwagen")))
                .andExpect(jsonPath("$.nummerplaat", is("1-IUY-234")));
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByMerk_thenReturnJsonVrachtwagen() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);
        vrachtwagenList.add(v4);

        mockMvc.perform(get("/vrachtwagens/{merk}", "Renault"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].merk", is("Renault")))
                .andExpect(jsonPath("$[1].nummerplaat", is("1-RGH-444")))
                .andExpect(jsonPath("$[1].model", is("Andere Vrachtwagen")))
                .andExpect(jsonPath("$[1].bedrijf", is("The Value Chain")))
                .andExpect(jsonPath("$[1].bouwjaar", is("2020")));
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByModel_thenReturnJsonVrachtwagens() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);
        vrachtwagenList.add(v3);
        vrachtwagenList.add(v4);

        mockMvc.perform(get("/vrachtwagens/{model}", "Vrachtwagen"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2021")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-IUY-234")))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")))
                .andExpect(jsonPath("$[0].merk", is("Volvo")));
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByBouwjaar_thenReturnJsonVrachtwagens() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);
        vrachtwagenList.add(v3);
        vrachtwagenList.add(v4);

        mockMvc.perform(get("/vrachtwagens/{bouwjaar}", "2020"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")))
                .andExpect(jsonPath("$[0].merk", is("Renault")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-RTY-555")))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2020")));
    }


    @Test
    public void whenPostVrachtwagen_thenReturnJsonVrachtwagen() throws Exception {
        Vrachtwagen vNew = new Vrachtwagen("6", "Saab", "Vrachtwagen", "2009", "3-OPE-345", "SAP Enterprises");

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
        mockMvc.perform(delete("/vrachtwagens/{nummerplaat}", "1-ARE-788")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoVrachtwagen_whenDeleteVrachtwagen_thenStatusNotFound() throws Exception {
        mockMvc.perform(delete("/vrachtwagens/{nummerplaat}", "9-POP-567")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }






}
