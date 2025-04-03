package ap.azubisteckbriefbackend.entities;

import java.io.Serializable;
import java.util.Objects;

public class BenotungSozialverhaltenId implements Serializable {
    private Long bewertung;
    private Long leistungsbewertungSozialverhalten;

    public BenotungSozialverhaltenId() {}

    public BenotungSozialverhaltenId(Long bewertung, Long leistungsbewertungSozialverhalten) {
        this.bewertung = bewertung;
        this.leistungsbewertungSozialverhalten = leistungsbewertungSozialverhalten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BenotungSozialverhaltenId that)) return false;
        return Objects.equals(bewertung, that.bewertung) &&
                Objects.equals(leistungsbewertungSozialverhalten, that.leistungsbewertungSozialverhalten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewertung, leistungsbewertungSozialverhalten);
    }
}

