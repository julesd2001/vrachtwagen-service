package fact.it.vrachtwagenservice.repository;

import fact.it.vrachtwagenservice.model.Vrachtwagen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VrachtwagenRepository extends MongoRepository<Vrachtwagen, String> {
    Vrachtwagen findVrachtwagenByNummerplaat(String nummerplaat);
    List<Vrachtwagen> findVrachtwagensByMerk(String merk);
    List<Vrachtwagen> findVrachtwagensByModel(String model);
    List<Vrachtwagen> findVrachtwagensByBouwjaar(String bouwjaar);
    List<Vrachtwagen> findVrachtwagensByBedrijf(String bedrijf);
    Vrachtwagen findVrachtwagenById(String id);

}
