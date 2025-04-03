package ap.azubisteckbriefbackend.entities;

import java.io.Serializable;
import java.util.Objects;

public class BenotungInhaltId implements Serializable {

    private Long bewertung;
    private Long leistungsbewertungInhalt;

    public BenotungInhaltId() {}

    public BenotungInhaltId(Long bewertung, Long leistungsbewertungInhalt) {
        this.bewertung = bewertung;
        this.leistungsbewertungInhalt = leistungsbewertungInhalt;
    }

    // equals() und hashCode() sind Pflicht für Composite Key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BenotungInhaltId)) return false;
        BenotungInhaltId that = (BenotungInhaltId) o;
        return Objects.equals(bewertung, that.bewertung) &&
                Objects.equals(leistungsbewertungInhalt, that.leistungsbewertungInhalt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewertung, leistungsbewertungInhalt);
    }
}
