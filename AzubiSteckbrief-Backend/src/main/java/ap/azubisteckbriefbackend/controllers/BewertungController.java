package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.dtos.BewertungRequestDTO;
import ap.azubisteckbriefbackend.dtos.LeistungsbewertungInhaltDTO;
import ap.azubisteckbriefbackend.entities.*;
import ap.azubisteckbriefbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bewertungen")
@CrossOrigin(origins = "http://localhost:4200")
public class BewertungController {

    @Autowired private BewertungRepository bewertungRepository;
    @Autowired private AuszubildendeRepository azubiRepository;
    @Autowired private ReferatRepository referatRepository;
    @Autowired private SchulungRepository schulungRepository;
    @Autowired private UnterpunktRepository unterpunktRepository;
    @Autowired private ErledigtesRepository erledigtesRepository;
    @Autowired private BenotungInhaltRepository benotungInhaltRepository;
    @Autowired private LeistungsbewertungInhaltRepository leistungsbewertungInhalteRepository;

    // NEU für Sozialverhalten
    @Autowired private LeistungsbewertungSozialverhaltenRepository leistungsbewertungSozialverhaltenRepository;
    @Autowired private BenotungSozialverhaltenRepository benotungSozialverhaltenRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createBewertung(@RequestBody BewertungRequestDTO dto) {
        if (dto.getAzubiId() == null) {
            return ResponseEntity.badRequest().body("azubiId fehlt");
        }

        Auszubildende azubi = azubiRepository.findById(dto.getAzubiId())
                .orElseThrow(() -> new RuntimeException("Azubi nicht gefunden"));

        Bewertung bewertung;

        if (dto.getBewertungId() != null) {
            // Bewertung überschreiben
            bewertung = bewertungRepository.findById(dto.getBewertungId())
                    .orElseThrow(() -> new RuntimeException("Bewertung nicht gefunden"));

            erledigtesRepository.deleteByBewertung(bewertung);
            benotungInhaltRepository.deleteByBewertung(bewertung);
            // NEU: Vorherige Sozialverhalten-Noten löschen
            benotungSozialverhaltenRepository.deleteByBewertung(bewertung);

        } else {
            // Neue Bewertung anlegen
            bewertung = new Bewertung();
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
        }

        // ✅ Erledigte Punkte speichern
        for (BewertungRequestDTO.PunktStatus punkt : dto.getErledigtePunkte()) {
            Unterpunkt unterpunkt = unterpunktRepository.findById(punkt.getUnterpunktId())
                    .orElseThrow(() -> new RuntimeException("Unterpunkt nicht gefunden: " + punkt.getUnterpunktId()));
            Erledigtes erledigtes = new Erledigtes(bewertung, unterpunkt, punkt.isStatus());
            erledigtesRepository.save(erledigtes);
        }

        // ✅ Fachliche Noten speichern
        if (dto.getInhaltNoten() != null) {
            for (BewertungRequestDTO.InhaltNote noteDto : dto.getInhaltNoten()) {
                LeistungsbewertungInhalt inhalt = leistungsbewertungInhalteRepository.findById(noteDto.getLeistungsbewertungInhaltId())
                        .orElseThrow(() -> new RuntimeException("Inhalt nicht gefunden: " + noteDto.getLeistungsbewertungInhaltId()));

                BenotungInhalt note = new BenotungInhalt();
                note.setBewertung(bewertung);
                note.setLeistungsbewertungInhalt(inhalt);
                note.setBenotung(noteDto.getNote());

                benotungInhaltRepository.save(note);
            }
        }

        // ✅ Sozialverhalten Noten speichern
        if (dto.getSozialverhaltenNoten() != null) {
            for (BewertungRequestDTO.SozialverhaltenNote noteDto : dto.getSozialverhaltenNoten()) {
                LeistungsbewertungSozialverhalten sozial = leistungsbewertungSozialverhaltenRepository.findById(noteDto.getLeistungsbewertungSozialverhaltenId())
                        .orElseThrow(() -> new RuntimeException("Sozialverhalten nicht gefunden: " + noteDto.getLeistungsbewertungSozialverhaltenId()));

                BenotungSozialverhalten note = new BenotungSozialverhalten();
                note.setBewertung(bewertung);
                note.setLeistungsbewertungSozialverhalten(sozial);
                note.setBenotung(noteDto.getNote());

                benotungSozialverhaltenRepository.save(note);
            }
        }

        return ResponseEntity.ok().body(Map.of("message", "Bewertung inkl. Punkte, Noten und Sozialverhalten gespeichert."));
    }

    @GetMapping("/bestehende")
    public ResponseEntity<?> findeVorhandeneBewertung(
            @RequestParam Long azubiId,
            @RequestParam(required = false) Long referatId,
            @RequestParam(required = false) Long schulungId
    ) {
        List<Bewertung> ergebnisse;

        if (referatId != null) {
            ergebnisse = bewertungRepository.findByAzubi_AzubiIdAndReferat_ReferatId(azubiId, referatId);
        } else if (schulungId != null) {
            ergebnisse = bewertungRepository.findByAzubi_AzubiIdAndSchulung_SchulungId(azubiId, schulungId);
        } else {
            return ResponseEntity.badRequest().body("referatId oder schulungId erforderlich.");
        }

        if (ergebnisse.isEmpty()) {
            return ResponseEntity.ok().build();
        }

        Bewertung vorhandene = ergebnisse.get(0);
        return ResponseEntity.ok(vorhandene.getBewertungId());
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

    @GetMapping("/leistungsbewertungInhalte")
    public List<LeistungsbewertungInhaltDTO> getLeistungsbewertungInhalte() {
        List<LeistungsbewertungInhalt> inhalte = leistungsbewertungInhalteRepository.findAll();

        return inhalte.stream()
                .map(i -> new LeistungsbewertungInhaltDTO(
                        i.getLeistungsbewertungInhaltId(),
                        i.getLeistungsbewertungInhalt(),
                        i.getLeistungsbewertungKategorie() != null ? i.getLeistungsbewertungKategorie().getLeistungsbewertungKategorie() : null
                ))
                .toList();
    }

    @GetMapping("/{bewertungId}/noten")
    public ResponseEntity<?> getNotenZurBewertung(@PathVariable Long bewertungId) {
        Bewertung bewertung = bewertungRepository.findById(bewertungId)
                .orElseThrow(() -> new RuntimeException("Bewertung nicht gefunden"));

        // ✅ Fachliche Noten
        List<BenotungInhalt> benotungen = benotungInhaltRepository.findByBewertung(bewertung);
        List<Map<String, Object>> inhaltNoten = benotungen.stream()
                .map(b -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("leistungsbewertungInhaltId", b.getLeistungsbewertungInhalt().getLeistungsbewertungInhaltId());
                    map.put("note", b.getBenotung());
                    return map;
                })
                .toList();

        // ✅ Sozialverhalten Noten
        List<BenotungSozialverhalten> sozialNoten = benotungSozialverhaltenRepository.findByBewertung(bewertung);
        List<Map<String, Object>> sozialverhaltenNoten = sozialNoten.stream()
                .map(s -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("leistungsbewertungSozialverhaltenId", s.getLeistungsbewertungSozialverhalten().getId());
                    map.put("note", s.getBenotung());
                    return map;
                })
                .toList();

        // ✅ Kombiniertes Response-Objekt
        Map<String, Object> response = new HashMap<>();
        response.put("inhaltNoten", inhaltNoten);
        response.put("sozialverhaltenNoten", sozialverhaltenNoten);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/leistungsbewertungSozialverhalten")
    public List<LeistungsbewertungSozialverhalten> getSozialverhalten() {
        return leistungsbewertungSozialverhaltenRepository.findAll();
    }
}
