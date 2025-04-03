package ap.azubisteckbriefbackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ausbildungsberufsbild {

    @Id
    @GeneratedValue
    @Column(name = "ausbildungsberufsbild_id")
    private Long ausbildungsberufsbildId;

    private String ausbildungsberufsbild;

    @Column(name = "zeitlicher_richtwert")
    private String zeitlicherRichtwert;

    @OneToMany(mappedBy = "ausbildungsberufsbild", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Vermittlung> vermittlungen;

    // Getter & Setter
    public Long getAusbildungsberufsbildId() {
        return ausbildungsberufsbildId;
    }

    public void setAusbildungsberufsbildId(Long ausbildungsberufsbildId) {
        this.ausbildungsberufsbildId = ausbildungsberufsbildId;
    }

    public String getAusbildungsberufsbild() {
        return ausbildungsberufsbild;
    }

    public void setAusbildungsberufsbild(String ausbildungsberufsbild) {
        this.ausbildungsberufsbild = ausbildungsberufsbild;
    }

    public String getZeitlicherRichtwert() {
        return zeitlicherRichtwert;
    }

    public void setZeitlicherRichtwert(String zeitlicherRichtwert) {
        this.zeitlicherRichtwert = zeitlicherRichtwert;
    }

    public List<Vermittlung> getVermittlungen() {
        return vermittlungen;
    }

    public void setVermittlungen(List<Vermittlung> vermittlungen) {
        this.vermittlungen = vermittlungen;
    }
}
