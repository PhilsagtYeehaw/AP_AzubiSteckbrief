package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Unterpunkt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnterpunktRepository extends JpaRepository<Unterpunkt, Long> {
    List<Unterpunkt> findByVermittlung_VermittlungId(Long vermittlungId);
}
