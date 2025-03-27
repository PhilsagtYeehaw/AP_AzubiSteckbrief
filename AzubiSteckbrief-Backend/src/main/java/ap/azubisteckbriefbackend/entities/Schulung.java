package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Schulung")
public class Schulung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schulung_id")
    private Long schulungId;

    private String schulung;

    // Getter & Setter
    public Long getSchulung_id() {
        return schulungId;
    }

    public void setSchulung_id(Long schulung_id) {
        this.schulungId = schulung_id;
    }

    public String getSchulung() {
        return schulung;
    }

    public void setSchulung(String schulung) {
        this.schulung = schulung;
    }
}
