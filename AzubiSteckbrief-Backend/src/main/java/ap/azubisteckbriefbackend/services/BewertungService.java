package ap.azubisteckbriefbackend.services;

import ap.azubisteckbriefbackend.dtos.BewertungRequestDTO;
import ap.azubisteckbriefbackend.entities.*;
import ap.azubisteckbriefbackend.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BewertungService {

    private final BewertungRepository bewertungRepo;
    private final AuszubildendeRepository azubiRepo;
    private final ReferatRepository referatRepo;
    private final SchulungRepository schulungRepo;
    private final UnterpunktRepository unterpunktRepo;
    private final ErledigtesRepository erledigtesRepo;

    public BewertungService(
            BewertungRepository bewertungRepo,
            AuszubildendeRepository azubiRepo,
            ReferatRepository referatRepo,
            SchulungRepository schulungRepo,
            UnterpunktRepository unterpunktRepo,
            ErledigtesRepository erledigtesRepo
    ) {
        this.bewertungRepo = bewertungRepo;
        this.azubiRepo = azubiRepo;
        this.referatRepo = referatRepo;
        this.schulungRepo = schulungRepo;
        this.unterpunktRepo = unterpunktRepo;
        this.erledigtesRepo = erledigtesRepo;
    }

    @Transactional
    public Long erstelleBewertung(BewertungRequestDTO dto) {
        Bewertung bewertung = new Bewertung();
        bewertung.setAzubi(azubiRepo.findById(dto.getAzubiId()).orElseThrow());

        if (dto.getReferatId() != null) {
            bewertung.setReferat(referatRepo.findById(dto.getReferatId()).orElseThrow());
        }

        if (dto.getSchulungId() != null) {
            bewertung.setSchulung(schulungRepo.findById(dto.getSchulungId()).orElseThrow());
        }

        bewertung = bewertungRepo.save(bewertung);

        for (BewertungRequestDTO.PunktStatus punktStatus : dto.getErledigtePunkte()) {
            Unterpunkt unterpunkt = unterpunktRepo.findById(punktStatus.getUnterpunktId()).orElseThrow();
            Erledigtes erledigtes = new Erledigtes(bewertung, unterpunkt, punktStatus.isStatus());
            erledigtesRepo.save(erledigtes);
        }

        return bewertung.getBewertungId();
    }
}
