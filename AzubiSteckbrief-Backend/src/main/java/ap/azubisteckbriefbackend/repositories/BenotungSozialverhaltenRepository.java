package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.BenotungSozialverhalten;
import ap.azubisteckbriefbackend.entities.Bewertung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenotungSozialverhaltenRepository extends JpaRepository<BenotungSozialverhalten, Long> {
    List<BenotungSozialverhalten> findByBewertung(Bewertung bewertung);
    void deleteByBewertung(Bewertung bewertung);
}
