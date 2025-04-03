package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
public class Erledigtes {

    @EmbeddedId
    private ErledigtesId id;

    @ManyToOne
    @MapsId("bewertungId")
    @JoinColumn(name = "bewertung_id")
    private Bewertung bewertung;

    @ManyToOne
    @MapsId("unterpunktId")
    @JoinColumn(name = "unterpunkt_id")
    private Unterpunkt unterpunkt;

    private Boolean status;

    public Erledigtes() {}

    public Erledigtes(Bewertung bewertung, Unterpunkt unterpunkt, Boolean status) {
        this.bewertung = bewertung;
        this.unterpunkt = unterpunkt;
        this.status = status;
        this.id = new ErledigtesId(bewertung.getBewertungId(), unterpunkt.getUnterpunkteId());
    }

    public ErledigtesId getId() {
        return id;
    }

    public void setId(ErledigtesId id) {
        this.id = id;
    }

    public Bewertung getBewertung() {
        return bewertung;
    }

    public void setBewertung(Bewertung bewertung) {
        this.bewertung = bewertung;
    }

    public Unterpunkt getUnterpunkt() {
        return unterpunkt;
    }

    public void setUnterpunkt(Unterpunkt unterpunkt) {
        this.unterpunkt = unterpunkt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
