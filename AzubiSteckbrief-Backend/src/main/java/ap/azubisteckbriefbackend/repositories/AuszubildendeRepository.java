package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Auszubildende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuszubildendeRepository extends JpaRepository<Auszubildende, Long> {
    List<Auszubildende> findAllByOrderByLehrjahrAsc();
}