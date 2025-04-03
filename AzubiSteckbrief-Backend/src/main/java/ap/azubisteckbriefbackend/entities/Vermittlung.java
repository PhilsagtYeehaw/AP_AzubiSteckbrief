package ap.azubisteckbriefbackend.entities;

import ap.azubisteckbriefbackend.dtos.UnterpunktMitStatusDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vermittlung {

    @Id
    @GeneratedValue
    @Column(name = "vermittlung_id")
    private Long vermittlungId;

    private String vermittlung;

    @ManyToOne
    @JoinColumn(name = "ausbildungsberufsbild_id")
    @JsonBackReference
    private Ausbildungsberufsbild ausbildungsberufsbild;

    @OneToMany(mappedBy = "vermittlung", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Unterpunkt> unterpunkte;

    @Transient
    private List<UnterpunktMitStatusDTO> unterpunkteDTO;

    // Getter & Setter
    public Long getVermittlungId() {
        return vermittlungId;
    }

    public void setVermittlungId(Long vermittlungId) {
        this.vermittlungId = vermittlungId;
    }

    public String getVermittlung() {
        return vermittlung;
    }

    public void setVermittlung(String vermittlung) {
        this.vermittlung = vermittlung;
    }

    public Ausbildungsberufsbild getAusbildungsberufsbild() {
        return ausbildungsberufsbild;
    }

    public void setAusbildungsberufsbild(Ausbildungsberufsbild ausbildungsberufsbild) {
        this.ausbildungsberufsbild = ausbildungsberufsbild;
    }

    public List<Unterpunkt> getUnterpunkte() {
        return unterpunkte;
    }

    public void setUnterpunkte(List<Unterpunkt> unterpunkte) {
        this.unterpunkte = unterpunkte;
    }

    public List<UnterpunktMitStatusDTO> getUnterpunkteDTO() {
        return unterpunkteDTO;
    }

    public void setUnterpunkteDTO(List<UnterpunktMitStatusDTO> unterpunkteDTO) {
        this.unterpunkteDTO = unterpunkteDTO;
    }
}
