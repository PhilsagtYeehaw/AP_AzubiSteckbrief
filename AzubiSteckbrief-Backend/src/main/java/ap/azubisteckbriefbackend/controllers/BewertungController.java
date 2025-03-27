package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.entities.Auszubildende;
import ap.azubisteckbriefbackend.entities.Bewertung;
import ap.azubisteckbriefbackend.repositories.AuszubildendeRepository;
import ap.azubisteckbriefbackend.repositories.BewertungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bewertungen")
@CrossOrigin(origins = "http://localhost:4200")
public class BewertungController {

    @Autowired
    private BewertungRepository bewertungRepository;

    @Autowired
    private AuszubildendeRepository azubiRepository;

    @PostMapping
    public ResponseEntity<Bewertung> createBewertung(@RequestBody Bewertung bewertung) {
        if (bewertung.getAzubi() == null || bewertung.getAzubi().getAzubi_id() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Optional: sicherstellen, dass Azubi existiert
        Auszubildende azubi = azubiRepository.findById(bewertung.getAzubi().getAzubi_id())
                .orElseThrow(() -> new RuntimeException("Azubi nicht gefunden"));

        bewertung.setAzubi(azubi);

        Bewertung saved = bewertungRepository.save(bewertung);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Bewertung> getBewertungen(
            @RequestParam Long azubiId,
            @RequestParam(required = false) Long referatId,
            @RequestParam(required = false) Long schulungId
    ) {
        // Beispielhaft – ggf. nach deinen Anforderungen anpassen
        if (referatId != null) {
            return bewertungRepository.findByAzubi_AzubiIdAndReferat_ReferatId(azubiId, referatId);
        } else if (schulungId != null) {
            return bewertungRepository.findByAzubi_AzubiIdAndSchulung_SchulungId(azubiId, schulungId);
        } else {
            return bewertungRepository.findByAzubi_AzubiId(azubiId);
        }
    }
}

