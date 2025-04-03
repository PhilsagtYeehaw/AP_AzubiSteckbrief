package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.dtos.BewertungRequestDTO;
import ap.azubisteckbriefbackend.entities.*;
import ap.azubisteckbriefbackend.repositories.*;
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

    @Autowired
    private ReferatRepository referatRepository;

    @Autowired
    private SchulungRepository schulungRepository;

    @Autowired
    private UnterpunktRepository unterpunktRepository;

    @Autowired
    private ErledigtesRepository erledigtesRepository;

    @PostMapping
    public ResponseEntity<?> createBewertung(@RequestBody BewertungRequestDTO dto) {
        if (dto.getAzubiId() == null) {
            return ResponseEntity.badRequest().body("azubiId fehlt");
        }

        Auszubildende azubi = azubiRepository.findById(dto.getAzubiId())
                .orElseThrow(() -> new RuntimeException("Azubi nicht gefunden"));

        Bewertung bewertung = new Bewertung();
        bewertung.setAzubi(azubi);

        if (dto.getReferatId() != null) {
            Referat referat = referatRepository.findById(dto.getReferatId())
                    .orElseThrow(() -> new RuntimeException("Referat nicht gefunden"));
            bewertung.setReferat(referat);
        }

        if (dto.getSchulungId() != null) {
            Schulung schulung = schulungRepository.findById(dto.getSchulungId())
                    .orElseThrow(() -> new RuntimeException("Schulung nicht gefunden"));
            bewertung.setSchulung(schulung);
        }

        bewertung = bewertungRepository.save(bewertung);

        for (BewertungRequestDTO.PunktStatus punkt : dto.getErledigtePunkte()) {
            Unterpunkt unterpunkt = unterpunktRepository.findById(punkt.getUnterpunktId())
                    .orElseThrow(() -> new RuntimeException("Unterpunkt nicht gefunden: " + punkt.getUnterpunktId()));
            Erledigtes erledigtes = new Erledigtes(bewertung, unterpunkt, punkt.isStatus());
            erledigtesRepository.save(erledigtes);
        }

        return ResponseEntity.ok("Bewertung inkl. Punkte gespeichert.");
    }

    @GetMapping
    public List<Bewertung> getBewertungen(
            @RequestParam Long azubiId,
            @RequestParam(required = false) Long referatId,
            @RequestParam(required = false) Long schulungId
    ) {
        if (referatId != null) {
            return bewertungRepository.findByAzubi_AzubiIdAndReferat_ReferatId(azubiId, referatId);
        } else if (schulungId != null) {
            return bewertungRepository.findByAzubi_AzubiIdAndSchulung_SchulungId(azubiId, schulungId);
        } else {
            return bewertungRepository.findByAzubi_AzubiId(azubiId);
        }
    }
}
