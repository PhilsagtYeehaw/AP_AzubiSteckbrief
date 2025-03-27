package ap.azubisteckbriefbackend.repositories;

import ap.azubisteckbriefbackend.entities.Bewertung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BewertungRepository extends JpaRepository<Bewertung, Long> {
    List<Bewertung> findByAzubi_AzubiId(Long azubiId);

    List<Bewertung> findByAzubi_AzubiIdAndReferat_ReferatId(Long azubiId, Long referatId);

    List<Bewertung> findByAzubi_AzubiIdAndSchulung_SchulungId(Long azubiId, Long schulungId);
}
