package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Vermittlung;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VermittlungRepository extends JpaRepository<Vermittlung, Long> {
    @EntityGraph(attributePaths = "unterpunkte")
    List<Vermittlung> findByAusbildungsberufsbild_AusbildungsberufsbildId(Long id);
}

