package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Bewertung")
public class Bewertung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewertung_id")
    private Long bewertungId;

    @ManyToOne
    @JoinColumn(name = "azubi_id", nullable = false)
    private Auszubildende azubi;

    @ManyToOne
    @JoinColumn(name = "referat_id", nullable = true)
    private Referat referat;

    @ManyToOne
    @JoinColumn(name = "schulung_id", nullable = true)
    private Schulung schulung;

    // Optional: weitere Felder wie Datum, Kommentare etc.

    // Getter + Setter

    public Long getBewertungId() {
        return bewertungId;
    }

    public void setBewertungId(Long bewertungId) {
        this.bewertungId = bewertungId;
    }

    public Auszubildende getAzubi() {
        return azubi;
    }

    public void setAzubi(Auszubildende azubi) {
        this.azubi = azubi;
    }

    public Referat getReferat() {
        return referat;
    }

    public void setReferat(Referat referat) {
        this.referat = referat;
    }

    public Schulung getSchulung() {
        return schulung;
    }

    public void setSchulung(Schulung schulung) {
        this.schulung = schulung;
    }
}
