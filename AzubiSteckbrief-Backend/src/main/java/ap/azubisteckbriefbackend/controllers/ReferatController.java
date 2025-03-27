package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.entities.Referat;
import ap.azubisteckbriefbackend.repositories.ReferatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/referate")
@CrossOrigin(origins = "http://localhost:4200")
public class ReferatController {

    @Autowired
    private ReferatRepository referatRepository;

    @GetMapping
    public List<Referat> getAllReferate() {
        return referatRepository.findAll();
    }
}
