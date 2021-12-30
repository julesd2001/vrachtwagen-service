package fact.it.vrachtwagenservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.vrachtwagenservice.model.Vrachtwagen;
import fact.it.vrachtwagenservice.repository.VrachtwagenRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VrachtwagenControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VrachtwagenRepository vrachtwagenRepository;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByBedrijf_thenReturnJsonVrachtwagens() throws Exception {
        Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "1-AER-234", "Ordina");
        Vrachtwagen v2 = new Vrachtwagen("2", "Renault", "Vrachtwagen", "2020", "1-RAE-432", "Ordina");
        Vrachtwagen v3 = new Vrachtwagen("3", "DAF", "Vrachtwagen", "2019", "1-POI-345", "Thomas More");

        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);
        vrachtwagenList.add(v3);

        given(vrachtwagenRepository.findVrachtwagensByBedrijf("Ordina")).willReturn(vrachtwagenList);

        mockMvc.perform(get("/vrachtwagens/{bedrijf}", "Ordina"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].merk", is("Volvo")))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2021")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-AER-234")))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")));
    }

    //ik heb geen idee of dit de juiste manier is om een test te schrijven voor als we maar 1 vrachtwagen willen terughalen dus ik laat het voorlopig zo
    @Test
    public void givenVrachtwagen_whenGetVrachtwagenByNummerplaat_thenReturnJsonVrachtwagen() throws Exception {
        Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "1-IUY-234", "Ordina");

        given(vrachtwagenRepository.findVrachtwagenByNummerplaat(v1.getNummerplaat())).willReturn(v1);
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByMerk_thenReturnJsonVrachtwagens() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "1-IUY-234", "Ordina");
        Vrachtwagen v2 = new Vrachtwagen("2", "Renault", "Vrachtwagen", "2020", "1-RTY-555", "Ordina");
        Vrachtwagen v3 = new Vrachtwagen("3", "DAF", "Betere vrachtwagen", "2018", "1-REZ-345", "Thomas More");
        Vrachtwagen v4 = new Vrachtwagen("4", "Renault", "Andere Vrachtwagen", "2020", "1-RGH-444", "The Value Chain");

        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);
        vrachtwagenList.add(v3);
        vrachtwagenList.add(v4);

        given(vrachtwagenRepository.findVrachtwagensByMerk("Renault")).willReturn(vrachtwagenList);

        mockMvc.perform(get("/vrachtwagens/{merk}", "Renault"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].merk", is("Renault")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2020")))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-RTY-555")));
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByModel_thenReturnJsonVrachtwagens() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "1-IUY-234", "Ordina");
        Vrachtwagen v2 = new Vrachtwagen("2", "Renault", "Vrachtwagen", "2020", "1-RTY-555", "Ordina");

        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);

        given(vrachtwagenRepository.findVrachtwagensByModel("Vrachtwagen")).willReturn(vrachtwagenList);

        mockMvc.perform(get("/vrachtwagens/{model}", "Vrachtwagen"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].merk", is("Volvo")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2021")))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-IUY-234")));
    }

    @Test
    public void givenVrachtwagen_whenGetVrachtwagensByBouwjaar_thenReturnJsonVrachtwagens() throws Exception {
        List<Vrachtwagen> vrachtwagenList = new ArrayList<>();
        Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "1-IUY-234", "Ordina");
        Vrachtwagen v2 = new Vrachtwagen("2", "Renault", "Vrachtwagen", "2020", "1-RTY-555", "Ordina");
        Vrachtwagen v4 = new Vrachtwagen("4", "Renault", "Andere Vrachtwagen", "2020", "1-RGH-444", "The Value Chain");

        vrachtwagenList.add(v1);
        vrachtwagenList.add(v2);
        vrachtwagenList.add(v4);

        given(vrachtwagenRepository.findVrachtwagensByBouwjaar("2020")).willReturn(vrachtwagenList);

        mockMvc.perform(get("/vrachtwagens/{bouwjaar}", "2020"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].merk", is("Volvo")))
                .andExpect(jsonPath("$[0].bouwjaar", is("2021")))
                .andExpect(jsonPath("$[0].model", is("Vrachtwagen")))
                .andExpect(jsonPath("$[0].bedrijf", is("Ordina")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-IUY-234")));
    }


    @Test
    public void whenPostVrachtwagen_thenReturnJsonVrachtwagen() throws Exception {
        Vrachtwagen newVrachtwagen = new Vrachtwagen("5", "Saab", "Vrachtwagen", "2009", "2-ERA-345", "SAP Enterprises");

        mockMvc.perform(post("/vrachtwagens")
                .content(mapper.writeValueAsString(newVrachtwagen))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merk", is("Saab")))
                .andExpect(jsonPath("$.model", is("Vrachtwagen")))
                .andExpect(jsonPath("$.bouwjaar", is("2009")))
                .andExpect(jsonPath("$.nummerplaat", is("2-ERA-345")))
                .andExpect(jsonPath("$.bedrijf", is("SAP Enterprises")));
    }

    @Test
    public void givenVrachtwagen_whenPutVrachtwagen_thenReturnJsonVrachtwagen() throws Exception {
        Vrachtwagen v1 = new Vrachtwagen("1", "Volvo", "Vrachtwagen", "2021", "9-ABC-123", "Ordina");

        given(vrachtwagenRepository.findVrachtwagenByNummerplaat("9-ABC-123")).willReturn(v1);

        Vrachtwagen updatedVrachtwagen = new Vrachtwagen("1", "Volvo", "Nieuwe Vrachtwagen", "2022", "1-AER-234", "Ordina");

        mockMvc.perform(put("/vrachtwagens/{nummerplaat}")
                .content(mapper.writeValueAsString(updatedVrachtwagen))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merk", is("Volvo")))
                .andExpect(jsonPath("$.model", is("Nieuwe Vrachtwagen")))
                .andExpect(jsonPath("$.bouwjaar", is("2022")))
                .andExpect(jsonPath("$.nummerplaat", is("1-AER-234")))
                .andExpect(jsonPath("$.bedrijf", is("Ordina")));
    }

    @Test
    public void givenVrachtwagen_whenDeleteVrachtwagen_thenStatusOk() throws Exception {
        Vrachtwagen deleteVrachtwagen = new Vrachtwagen("7", "test", "test model", "2009", "9-ABC-123", "Thomas More");
        given(vrachtwagenRepository.findVrachtwagenByNummerplaat("9-ABC-123")).willReturn(deleteVrachtwagen);
        mockMvc.perform(delete("/vrachtwagens/{nummerplaat}", "9-ABC-123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenVrachtwagen_whenDeleteVrachtwagen_thenStatusNotFound() throws Exception {
        given(vrachtwagenRepository.findVrachtwagenByNummerplaat("8-FDG-432")).willReturn(null);

        mockMvc.perform(delete("/vrachtwagens/{nummerplaat}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }






}
