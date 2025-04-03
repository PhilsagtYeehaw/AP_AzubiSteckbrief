// LeistungsbewertungInhalt.java
package ap.azubisteckbriefbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Leistungsbewertung_Inhalt")
public class LeistungsbewertungInhalt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leistungsbewertung_inhalt_id")
    private Long leistungsbewertungInhaltId;

    @Column(name = "leistungsbewertung_inhalt")
    private String leistungsbewertungInhalt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leistungsbewertung_kategorie_id")
    @JsonIgnore
    private LeistungsbewertungKategorie leistungsbewertungKategorie;

    public Long getLeistungsbewertungInhaltId() {
        return leistungsbewertungInhaltId;
    }

    public void setLeistungsbewertungInhaltId(Long leistungsbewertungInhaltId) {
        this.leistungsbewertungInhaltId = leistungsbewertungInhaltId;
    }

    public String getLeistungsbewertungInhalt() {
        return leistungsbewertungInhalt;
    }

    public void setLeistungsbewertungInhalt(String leistungsbewertungInhalt) {
        this.leistungsbewertungInhalt = leistungsbewertungInhalt;
    }

    public LeistungsbewertungKategorie getLeistungsbewertungKategorie() {
        return leistungsbewertungKategorie;
    }

    public void setLeistungsbewertungKategorie(LeistungsbewertungKategorie leistungsbewertungKategorie) {
        this.leistungsbewertungKategorie = leistungsbewertungKategorie;
    }
}