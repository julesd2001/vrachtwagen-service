package fact.it.vrachtwagenservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vrachtwagens")
public class Vrachtwagen {
    private String id;
    @Id
    private String nummerplaat;
    private String merk, model, bouwjaar;
    //gaan we van bedrijf gewoon een string maken?
    private String bedrijf;

    public Vrachtwagen() {
    }


    public Vrachtwagen(String id, String merk, String model, String bouwjaar, String nummerplaat, String bedrijf) {
        setId(id);
        setMerk(merk);
        setModel(model);
        setBouwjaar(bouwjaar);
        setNummerplaat(nummerplaat);
        setBedrijf(bedrijf);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(String bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

    public String getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(String bedrijf) {
        this.bedrijf = bedrijf;
    }
}
