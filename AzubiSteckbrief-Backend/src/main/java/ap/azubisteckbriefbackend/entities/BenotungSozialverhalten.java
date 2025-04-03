package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Benotung_Sozialverhalten")
@IdClass(BenotungSozialverhaltenId.class)
public class BenotungSozialverhalten {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bewertung_id")
    private Bewertung bewertung;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leistungsbewertung_sozialverhalten_id")
    private LeistungsbewertungSozialverhalten leistungsbewertungSozialverhalten;

    private String benotung;

    // Getter & Setter

    public Bewertung getBewertung() {
        return bewertung;
    }

    public void setBewertung(Bewertung bewertung) {
        this.bewertung = bewertung;
    }

    public LeistungsbewertungSozialverhalten getLeistungsbewertungSozialverhalten() {
        return leistungsbewertungSozialverhalten;
    }

    public void setLeistungsbewertungSozialverhalten(LeistungsbewertungSozialverhalten leistungsbewertungSozialverhalten) {
        this.leistungsbewertungSozialverhalten = leistungsbewertungSozialverhalten;
    }

    public String getBenotung() {
        return benotung;
    }

    public void setBenotung(String benotung) {
        this.benotung = benotung;
    }
}
