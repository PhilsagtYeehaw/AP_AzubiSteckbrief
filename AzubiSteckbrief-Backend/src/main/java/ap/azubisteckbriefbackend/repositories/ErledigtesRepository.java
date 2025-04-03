package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Erledigtes;
import ap.azubisteckbriefbackend.entities.ErledigtesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErledigtesRepository extends JpaRepository<Erledigtes, ErledigtesId> {

    List<Erledigtes> findByBewertung_BewertungId(Long bewertungId);

    List<Erledigtes> findByUnterpunkt_UnterpunkteId(Long unterpunktId);

    boolean existsById(ErledigtesId id);
}

//List<Erledigtes> findByAzubi_AzubiId(Long azubiId); // NEU


