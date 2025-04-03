package ap.azubisteckbriefbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Unterpunkt {

    @Id
    @GeneratedValue
    @Column(name = "unterpunkt_id")
    private Long unterpunkteId;

    private String unterpunkt;

    @ManyToOne
    @JoinColumn(name = "vermittlung_id")
    @JsonBackReference
    private Vermittlung vermittlung;

    // Getter & Setter
    public Long getUnterpunkteId() {
        return unterpunkteId;
    }

    public void setUnterpunkteId(Long unterpunkteId) {
        this.unterpunkteId = unterpunkteId;
    }

    public String getUnterpunkt() {
        return unterpunkt;
    }

    public void setUnterpunkt(String unterpunkt) {
        this.unterpunkt = unterpunkt;
    }

    public Vermittlung getVermittlung() {
        return vermittlung;
    }

    public void setVermittlung(Vermittlung vermittlung) {
        this.vermittlung = vermittlung;
    }
}
