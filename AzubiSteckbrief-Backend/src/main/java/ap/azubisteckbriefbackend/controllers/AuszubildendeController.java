package ap.azubisteckbriefbackend.controllers;

import ap.azubisteckbriefbackend.entities.Auszubildende;
import ap.azubisteckbriefbackend.repositories.AuszubildendeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auszubildende")
@CrossOrigin(origins = "http://localhost:4200")
public class AuszubildendeController {

    @Autowired
    private AuszubildendeRepository auszubildendeRepository;

    @GetMapping
    public List<Auszubildende> getAllAzubis() {
        List<Auszubildende> list = auszubildendeRepository.findAllByOrderByLehrjahrAsc();
        System.out.println("Gefundene Azubis: " + list.size());
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auszubildende> getAzubiById(@PathVariable Long id) {
        return auszubildendeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}