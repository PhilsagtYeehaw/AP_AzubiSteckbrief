// LeistungsbewertungKategorie.java
package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Leistungsbewertung_Kategorie")
public class LeistungsbewertungKategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leistungsbewertung_kategorie_id")
    private Long leistungsbewertungKategorieId;

    @Column(name = "leistungsbewertung_kategorie")
    private String leistungsbewertungKategorie;

    public Long getLeistungsbewertungKategorieId() {
        return leistungsbewertungKategorieId;
    }

    public void setLeistungsbewertungKategorieId(Long leistungsbewertungKategorieId) {
        this.leistungsbewertungKategorieId = leistungsbewertungKategorieId;
    }

    public String getLeistungsbewertungKategorie() {
        return leistungsbewertungKategorie;
    }

    public void setLeistungsbewertungKategorie(String leistungsbewertungKategorie) {
        this.leistungsbewertungKategorie = leistungsbewertungKategorie;
    }
}

