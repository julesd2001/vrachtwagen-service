package fact.it.vrachtwagenservice.controller;

import fact.it.vrachtwagenservice.model.Vrachtwagen;
import fact.it.vrachtwagenservice.repository.VrachtwagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class VrachtwagenController {

    @Autowired
    private VrachtwagenRepository vrachtwagenRepository;

    @PostConstruct
    public void fillDB() {
        if (vrachtwagenRepository.count() == 0) {
            vrachtwagenRepository.save(new Vrachtwagen("1", "Renault", "Midlum 190", "2009", "1-ABC-123", "Ordina"));
            vrachtwagenRepository.save(new Vrachtwagen("2", "DAF", "XF", "2008", "2-DEF-456", "The Value Chain"));
            vrachtwagenRepository.save(new Vrachtwagen("3", "DAF", "CF", "2009", "3-GHI-789", "Thomas More"));
        }
    }

    @GetMapping("/vrachtwagens/{bouwjaar}")
    public List<Vrachtwagen> getVrachtwagensByBouwjaar(@PathVariable String bouwjaar) {
        return vrachtwagenRepository.findVrachtwagensByBouwjaar(bouwjaar);
    }

    @GetMapping("/vrachtwagens/{bedrijf}")
    public List<Vrachtwagen> getVrachtwagensByBedrijf(@PathVariable String bedrijf) {
        return vrachtwagenRepository.findVrachtwagensByBedrijf(bedrijf);
    }

    @GetMapping("/vrachtwagens/{nummerplaat}")
    public Vrachtwagen getVrachtwagenByNummerplaat(@PathVariable String nummerplaat) {
        return vrachtwagenRepository.findVrachtwagenByNummerplaat(nummerplaat);
    }



    @GetMapping("/vrachtwagens")
    public List<Vrachtwagen> vrachtwagenList() {
        return vrachtwagenRepository.findAll();
    }

    @PostMapping("/vrachtwagens")
    public Vrachtwagen addVrachtwagen(@RequestBody Vrachtwagen vrachtwagen) {
        vrachtwagenRepository.save(vrachtwagen);
        return vrachtwagen;
    }




}
