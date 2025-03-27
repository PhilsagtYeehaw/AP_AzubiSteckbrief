package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
public class Auszubildende {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "azubi_id")
    private Long azubiId;
    private String vorname;
    private String nachname;
    private String email;
    private int lehrjahr;

    public Long getAzubi_id() { return azubiId; }
    public void setAzubi_id(Long azubi_id) { this.azubiId = azubi_id; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getLehrjahr() { return lehrjahr; }
    public void setLehrjahr(int lehrjahr) { this.lehrjahr = lehrjahr; }
}