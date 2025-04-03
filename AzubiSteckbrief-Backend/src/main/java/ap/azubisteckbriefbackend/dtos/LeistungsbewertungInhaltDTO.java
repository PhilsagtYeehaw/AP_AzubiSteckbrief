package ap.azubisteckbriefbackend.dtos;

public class LeistungsbewertungInhaltDTO {
    private Long id;
    private String inhalt;
    private String kategorie;

    // Konstruktor
    public LeistungsbewertungInhaltDTO(Long id, String inhalt, String kategorie) {
        this.id = id;
        this.inhalt = inhalt;
        this.kategorie = kategorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }
}
