package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.BenotungInhalt;
import ap.azubisteckbriefbackend.entities.BenotungInhaltId;
import ap.azubisteckbriefbackend.entities.Bewertung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenotungInhaltRepository extends JpaRepository<BenotungInhalt, BenotungInhaltId> {
    List<BenotungInhalt> findByBewertung_BewertungId(Long bewertungId);
    void deleteByBewertung(Bewertung bewertung);
    List<BenotungInhalt> findByBewertung(Bewertung bewertung);
}

