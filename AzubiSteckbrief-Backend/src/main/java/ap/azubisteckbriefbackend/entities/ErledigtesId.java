package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ErledigtesId implements Serializable {

    private Long bewertungId;
    private Long unterpunktId;

    public ErledigtesId() {}

    public ErledigtesId(Long bewertungId, Long unterpunktId) {
        this.bewertungId = bewertungId;
        this.unterpunktId = unterpunktId;
    }

    public Long getBewertungId() {
        return bewertungId;
    }

    public void setBewertungId(Long bewertungId) {
        this.bewertungId = bewertungId;
    }

    public Long getUnterpunktId() {
        return unterpunktId;
    }

    public void setUnterpunktId(Long unterpunktId) {
        this.unterpunktId = unterpunktId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErledigtesId that)) return false;
        return Objects.equals(bewertungId, that.bewertungId) &&
                Objects.equals(unterpunktId, that.unterpunktId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewertungId, unterpunktId);
    }
}
