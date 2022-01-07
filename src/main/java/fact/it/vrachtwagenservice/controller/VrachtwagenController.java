package fact.it.vrachtwagenservice.controller;

import fact.it.vrachtwagenservice.model.Vrachtwagen;
import fact.it.vrachtwagenservice.repository.VrachtwagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;

@RestController
public class VrachtwagenController {

    @Autowired
    private VrachtwagenRepository vrachtwagenRepository;

    @PostConstruct
    public void fillDB() {
        if (vrachtwagenRepository.count() == 0) {
            vrachtwagenRepository.save(new Vrachtwagen("1", "Volvo", "FH16", "2021", "1-UAE-451", "Ordina"));
            vrachtwagenRepository.save(new Vrachtwagen("2", "Renault", "Trucks T", "2020", "1-IKJ-565", "Ordina"));
        }

        System.out.println("Test: " + vrachtwagenRepository.findAll().size());
    }

    @GetMapping("/vrachtwagens")
    public List<Vrachtwagen> getAllVrachtwagens() {
        return vrachtwagenRepository.findAll();
    }

    @GetMapping("/vrachtwagens/bouwjaar/{bouwjaar}")
    public List<Vrachtwagen> getVrachtwagensByBouwjaar(@PathVariable String bouwjaar) {
        return vrachtwagenRepository.findVrachtwagensByBouwjaar(bouwjaar);
    }

    @GetMapping("/vrachtwagens/bedrijf/{bedrijf}")
    public List<Vrachtwagen> getVrachtwagensByBedrijf(@PathVariable String bedrijf) {
        return vrachtwagenRepository.findVrachtwagensByBedrijf(bedrijf);
    }

    @GetMapping("/vrachtwagens/nummerplaat/{nummerplaat}")
    public Vrachtwagen getVrachtwagenByNummerplaat(@PathVariable String nummerplaat) {
        return vrachtwagenRepository.findVrachtwagenByNummerplaat(nummerplaat);
    }

    @GetMapping("/vrachtwagens/merk/{merk}")
    public List<Vrachtwagen> getVrachtwagensByMerk(@PathVariable String merk) {
        String merkUpper = merk.substring(0,1).toUpperCase() + merk.substring(1);
        return vrachtwagenRepository.findVrachtwagensByMerk(merkUpper);
    }

    @GetMapping("/vrachtwagens/model/{model}")
    public List<Vrachtwagen> getVrachtwagensByModel(@PathVariable String model) {
        return vrachtwagenRepository.findVrachtwagensByModel(model);
    }

    @PostMapping("/vrachtwagens")
    public Vrachtwagen addVrachtwagen(@RequestBody Vrachtwagen vrachtwagen) {
        vrachtwagenRepository.save(vrachtwagen);
        return vrachtwagen;
    }

    @PutMapping("/vrachtwagens/nummerplaat/{nummerplaat}")
    public Vrachtwagen editVrachtwagen(@RequestBody Vrachtwagen vrachtwagen, @PathVariable String nummerplaat) {
        Vrachtwagen retrievedVrachtwagen = vrachtwagenRepository.findVrachtwagenByNummerplaat(nummerplaat);
        retrievedVrachtwagen.setMerk(vrachtwagen.getMerk());
        retrievedVrachtwagen.setModel(vrachtwagen.getModel());
        retrievedVrachtwagen.setBouwjaar(vrachtwagen.getBouwjaar());
        retrievedVrachtwagen.setNummerplaat(vrachtwagen.getNummerplaat());
        retrievedVrachtwagen.setBedrijf(vrachtwagen.getBedrijf());

        vrachtwagenRepository.save(retrievedVrachtwagen);
        return retrievedVrachtwagen;
    }

    @DeleteMapping("/vrachtwagens/nummerplaat/{nummerplaat}")
    public ResponseEntity deleteVrachtwagen(@PathVariable String nummerplaat) {
        Vrachtwagen vrachtwagen = vrachtwagenRepository.findVrachtwagenByNummerplaat(nummerplaat);
        if (vrachtwagen != null) {
            vrachtwagenRepository.delete(vrachtwagen);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
