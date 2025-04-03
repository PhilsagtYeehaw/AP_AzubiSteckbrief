package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@IdClass(BenotungInhaltId.class)
@Table(name = "Benotung_Inhalt")
public class BenotungInhalt {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bewertung_id")
    private Bewertung bewertung;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leistungsbewertung_inhalt_id")
    private LeistungsbewertungInhalt leistungsbewertungInhalt;

    private String benotung; // z.B. "1", "2", ..., "6"

    public String getBenotung() {
        return benotung;
    }

    public void setBenotung(String benotung) {
        this.benotung = benotung;
    }

    public Bewertung getBewertung() {
        return bewertung;
    }

    public void setBewertung(Bewertung bewertung) {
        this.bewertung = bewertung;
    }

    public LeistungsbewertungInhalt getLeistungsbewertungInhalt() {
        return leistungsbewertungInhalt;
    }

    public void setLeistungsbewertungInhalt(LeistungsbewertungInhalt leistungsbewertungInhalt) {
        this.leistungsbewertungInhalt = leistungsbewertungInhalt;
    }
}
