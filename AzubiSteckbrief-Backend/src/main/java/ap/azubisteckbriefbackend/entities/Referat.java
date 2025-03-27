package ap.azubisteckbriefbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Referat")
public class Referat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referat_id")
    private Long referatId;

    private String abteilung;
    private String referatsgruppe;
    private int referatsgruppennummer;

    // Getter & Setter
    public Long getReferat_id() {
        return referatId;
    }

    public void setReferat_id(Long referat_id) {
        this.referatId = referat_id;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getReferatsgruppe() {
        return referatsgruppe;
    }

    public void setReferatsgruppe(String referatsgruppe) {
        this.referatsgruppe = referatsgruppe;
    }

    public int getReferatsgruppennummer() {
        return referatsgruppennummer;
    }

    public void setReferatsgruppennummer(int referatsgruppennummer) {
        this.referatsgruppennummer = referatsgruppennummer;
    }
}
