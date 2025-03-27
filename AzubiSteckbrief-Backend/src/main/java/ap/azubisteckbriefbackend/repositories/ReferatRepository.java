package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Referat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferatRepository extends JpaRepository<Referat, Long> {
}
