package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Schulung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchulungRepository extends JpaRepository<Schulung, Long> {
}
