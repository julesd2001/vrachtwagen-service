package fact.it.vrachtwagenservice.controller;

import fact.it.vrachtwagenservice.model.Vrachtwagen;
import fact.it.vrachtwagenservice.repository.VrachtwagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            vrachtwagenRepository.save(new Vrachtwagen("1", "Volvo", "FH16", "2021", "1-UAE-451", "Ordina"));
            vrachtwagenRepository.save(new Vrachtwagen("2", "Renault", "Trucks T", "2020", "1-IKJ-565", "Ordina"));
        }

        System.out.println("Test: " + vrachtwagenRepository.findAll().size());
    }

    @GetMapping("/vrachtwagens")
    public List<Vrachtwagen> getAllVrachtwagens() {
        return vrachtwagenRepository.findAll();
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

    @GetMapping("/vrachtwagens/{merk}")
    public List<Vrachtwagen> getVrachtwagensByMerk(@PathVariable String merk) {
        return vrachtwagenRepository.findVrachtwagensByMerk(merk);
    }

    @GetMapping("/vrachtwagens/{model}")
    public List<Vrachtwagen> getVrachtwagensByModel(@PathVariable String model) {
        return vrachtwagenRepository.findVrachtwagensByModel(model);
    }

    @GetMapping("/vrachtwagens/{bouwjaar}")
    public List<Vrachtwagen> getVrachtwagensByBouwjaar(@PathVariable String bouwjaar) {
        return vrachtwagenRepository.findVrachtwagensByBouwjaar(bouwjaar);
    }

    @GetMapping("/vrachtwagens/{bedrijf}")
    public List<Vrachtwagen> getVrachtwagensByBedrijf(@PathVariable String bedrijf) {
        return vrachtwagenRepository.findVrachtwagensByBedrijf(bedrijf);
    }

    @PostMapping("/vrachtwagens")
    public Vrachtwagen addVrachtwagen(@RequestBody Vrachtwagen vrachtwagen) {
        vrachtwagenRepository.save(vrachtwagen);
        return vrachtwagen;
    }
    //kan je de put functie zelfs maken door te zoeken op nummerplaat? geen idee dus ik heb het gewoon op ID voorlopig gelaten hier
    @PutMapping("/vrachtwagens/{id}")
    public Vrachtwagen editVrachtwagen(@RequestBody Vrachtwagen vrachtwagen, @PathVariable String id) {
        Vrachtwagen retrievedVrachtwagen = vrachtwagenRepository.findVrachtwagenById(id);
        retrievedVrachtwagen.setMerk(vrachtwagen.getMerk());
        retrievedVrachtwagen.setModel(vrachtwagen.getModel());
        retrievedVrachtwagen.setBouwjaar(vrachtwagen.getBouwjaar());
        retrievedVrachtwagen.setNummerplaat(vrachtwagen.getNummerplaat());
        retrievedVrachtwagen.setBedrijf(vrachtwagen.getBedrijf());

        vrachtwagenRepository.save(retrievedVrachtwagen);
        return retrievedVrachtwagen;
    }
    //mogen ID's gebruikt worden in de delete en putmappings of hier ook niet? ik laat het hier voorlopig staan nu
    @DeleteMapping("/vrachtwagens/{nummerplaat}")
    public ResponseEntity deleteVrachtwagen(@PathVariable String nummerplaat) {
        Vrachtwagen vrachtwagen = vrachtwagenRepository.findVrachtwagenById(nummerplaat);
        if (vrachtwagen != null) {
            vrachtwagenRepository.delete(vrachtwagen);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
