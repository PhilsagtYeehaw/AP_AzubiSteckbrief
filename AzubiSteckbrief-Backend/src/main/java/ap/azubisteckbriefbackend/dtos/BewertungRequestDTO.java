package ap.azubisteckbriefbackend.dtos;

import java.util.List;

public class BewertungRequestDTO {
    private Long azubiId;
    private Long referatId;
    private Long schulungId;
    private Long bewertungId; // 👈 HINZUGEFÜGT
    private List<PunktStatus> erledigtePunkte;

    public Long getAzubiId() {
        return azubiId;
    }

    public void setAzubiId(Long azubiId) {
        this.azubiId = azubiId;
    }

    public Long getReferatId() {
        return referatId;
    }

    public void setReferatId(Long referatId) {
        this.referatId = referatId;
    }

    public Long getSchulungId() {
        return schulungId;
    }

    public void setSchulungId(Long schulungId) {
        this.schulungId = schulungId;
    }

    public Long getBewertungId() {
        return bewertungId;
    }

    public void setBewertungId(Long bewertungId) {
        this.bewertungId = bewertungId;
    }

    public List<PunktStatus> getErledigtePunkte() {
        return erledigtePunkte;
    }

    public void setErledigtePunkte(List<PunktStatus> erledigtePunkte) {
        this.erledigtePunkte = erledigtePunkte;
    }

    public static class PunktStatus {
        private Long unterpunktId;
        private boolean status;

        public Long getUnterpunktId() {
            return unterpunktId;
        }

        public void setUnterpunktId(Long unterpunktId) {
            this.unterpunktId = unterpunktId;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
