package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.entities.Schulung;
import ap.azubisteckbriefbackend.repositories.SchulungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schulungen")
@CrossOrigin(origins = "http://localhost:4200")
public class SchulungController {

    @Autowired
    private SchulungRepository schulungRepository;

    @GetMapping
    public List<Schulung> getAllSchulungen() {
        return schulungRepository.findAll();
    }
}
