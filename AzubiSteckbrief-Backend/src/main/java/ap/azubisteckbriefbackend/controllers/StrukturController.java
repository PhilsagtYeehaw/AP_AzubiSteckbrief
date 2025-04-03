package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.dtos.UnterpunktMitStatusDTO;
import ap.azubisteckbriefbackend.entities.*;
import ap.azubisteckbriefbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vermittlungsstruktur")
@CrossOrigin(origins = "http://localhost:4200")
public class StrukturController {

    @Autowired
    private AusbildungsberufsbildRepository ausbildungsRepo;
    @Autowired
    private VermittlungRepository vermittlungRepo;
    @Autowired
    private UnterpunktRepository unterpunktRepo;
    @Autowired
    private ErledigtesRepository erledigtesRepo;
    @Autowired
    private BewertungRepository bewertungRepo;

    @GetMapping("/berufsbilder")
    public List<Ausbildungsberufsbild> alleBerufsbilder() {
        return ausbildungsRepo.findAll();
    }

    @GetMapping("/vermittlungen/{berufsbildId}")
    public List<Vermittlung> vermittlungen(@PathVariable Long berufsbildId) {
        return vermittlungRepo.findByAusbildungsberufsbild_AusbildungsberufsbildId(berufsbildId);
    }

    @GetMapping("/unterpunkte/{vermittelungId}")
    public List<Unterpunkt> unterpunkte(@PathVariable Long vermittelungId) {
        return unterpunktRepo.findByVermittlung_VermittlungId(vermittelungId);
    }

    @GetMapping("/erledigtes/{bewertungId}")
    public List<Erledigtes> getErledigtes(@PathVariable Long bewertungId) {
        return erledigtesRepo.findByBewertung_BewertungId(bewertungId);
    }

    @PostMapping("/erledigtes")
    public ResponseEntity<List<Erledigtes>> speichern(@RequestBody List<Erledigtes> eintraege) {
        eintraege.forEach(e -> {
            if (e.getId() == null && e.getBewertung() != null && e.getUnterpunkt() != null) {
                e.setId(new ErledigtesId(
                        e.getBewertung().getBewertungId(),
                        e.getUnterpunkt().getUnterpunkteId()
                ));
            }
        });
        List<Erledigtes> gespeichert = erledigtesRepo.saveAll(eintraege);
        return ResponseEntity.ok(gespeichert);
    }

    @GetMapping
    public ResponseEntity<?> ganzeStruktur() {
        try {
            List<Ausbildungsberufsbild> berufsbilder = ausbildungsRepo.findAll();
            berufsbilder.forEach(beruf -> {
                List<Vermittlung> vermittlungen = vermittlungRepo.findByAusbildungsberufsbild_AusbildungsberufsbildId(beruf.getAusbildungsberufsbildId());
                vermittlungen.forEach(vermittlung -> {
                    List<Unterpunkt> unterpunkte = unterpunktRepo.findByVermittlung_VermittlungId(vermittlung.getVermittlungId());
                    vermittlung.setUnterpunkte(unterpunkte);
                });
                beruf.setVermittlungen(vermittlungen);
            });

            return ResponseEntity.ok(berufsbilder);

        } catch (Exception e) {
            e.printStackTrace(); // für die Konsole
            return ResponseEntity.internalServerError().body("Fehler beim Laden der Struktur: " + e.getMessage());
        }
    }

    @GetMapping("/{azubiId}")
    public ResponseEntity<?> ganzeStrukturMitStatus(@PathVariable Long azubiId) {
        try {
            // Alle Bewertungen des Azubis
            List<Bewertung> bewertungen = bewertungRepo.findByAzubi_AzubiId(azubiId);

            // Alle Erledigtes-Einträge zu den Bewertungen
            List<Erledigtes> erledigte = new ArrayList<>();
            bewertungen.forEach(b -> erledigte.addAll(
                    erledigtesRepo.findByBewertung_BewertungId(b.getBewertungId())
            ));

            // Map von UnterpunktId -> Status
            Map<Long, Boolean> statusMap = erledigte.stream()
                    .collect(Collectors.toMap(e -> e.getUnterpunkt().getUnterpunkteId(), Erledigtes::getStatus));

            List<Ausbildungsberufsbild> berufsbilder = ausbildungsRepo.findAll();

            berufsbilder.forEach(beruf -> {
                List<Vermittlung> vermittlungen = vermittlungRepo.findByAusbildungsberufsbild_AusbildungsberufsbildId(beruf.getAusbildungsberufsbildId());

                vermittlungen.forEach(vermittlung -> {
                    List<Unterpunkt> unterpunkte = unterpunktRepo.findByVermittlung_VermittlungId(vermittlung.getVermittlungId());

                    List<UnterpunktMitStatusDTO> dtoList = unterpunkte.stream()
                            .map(u -> new UnterpunktMitStatusDTO(
                                    u.getUnterpunkteId(),
                                    u.getUnterpunkt(),
                                    statusMap.getOrDefault(u.getUnterpunkteId(), false)
                            )).toList();

                    vermittlung.setUnterpunkteDTO(dtoList);
                });

                beruf.setVermittlungen(vermittlungen);
            });

            return ResponseEntity.ok(berufsbilder);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Fehler beim Laden: " + e.getMessage());
        }
    }

}
