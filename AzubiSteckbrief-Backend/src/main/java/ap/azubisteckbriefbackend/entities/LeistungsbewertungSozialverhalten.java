package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Leistungsbewertung_Sozialverhalten")
public class LeistungsbewertungSozialverhalten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leistungsbewertung_sozialverhalten_id")
    private Long id;

    @Column(name = "leistungsbewertung_sozialverhalten")
    private String sozialverhalten;

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSozialverhalten() {
        return sozialverhalten;
    }

    public void setSozialverhalten(String sozialverhalten) {
        this.sozialverhalten = sozialverhalten;
    }
}
