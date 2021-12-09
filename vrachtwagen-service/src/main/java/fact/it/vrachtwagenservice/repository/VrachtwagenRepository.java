package fact.it.vrachtwagenservice.repository;

import fact.it.vrachtwagenservice.model.Vrachtwagen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VrachtwagenRepository extends MongoRepository<Vrachtwagen, String> {
    Vrachtwagen findVrachtwagenByNummerplaat(String nummerplaat);
    List<Vrachtwagen> findVrachtwagensByBedrijf(String bedrijf);
    List<Vrachtwagen> findVrachtwagensByBouwjaar(String bouwjaar);
    //kan uitbreiding nog gebruiken

}
