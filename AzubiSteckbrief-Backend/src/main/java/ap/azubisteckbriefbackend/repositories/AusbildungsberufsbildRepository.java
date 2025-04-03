package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Ausbildungsberufsbild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AusbildungsberufsbildRepository extends JpaRepository<Ausbildungsberufsbild, Long> {}

