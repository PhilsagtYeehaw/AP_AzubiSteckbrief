package ap.azubisteckbriefbackend.dtos;

import java.util.List;

public class BewertungRequestDTO {
    private Long azubiId;
    private Long referatId;
    private Long schulungId;
    private Long bewertungId; // 👈 HINZUGEFÜGT
    private List<PunktStatus> erledigtePunkte;
    private List<InhaltNote> inhaltNoten; // 👈 NEU

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

    public List<InhaltNote> getInhaltNoten() {
        return inhaltNoten;
    }

    public void setInhaltNoten(List<InhaltNote> inhaltNoten) {
        this.inhaltNoten = inhaltNoten;
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

    public static class InhaltNote {
        private Long leistungsbewertungInhaltId;
        private String note;

        public Long getLeistungsbewertungInhaltId() {
            return leistungsbewertungInhaltId;
        }

        public void setLeistungsbewertungInhaltId(Long leistungsbewertungInhaltId) {
            this.leistungsbewertungInhaltId = leistungsbewertungInhaltId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }

    public static class SozialverhaltenNote {
        private Long leistungsbewertungSozialverhaltenId;
        private String note;

        public Long getLeistungsbewertungSozialverhaltenId() {
            return leistungsbewertungSozialverhaltenId;
        }

        public void setLeistungsbewertungSozialverhaltenId(Long leistungsbewertungSozialverhaltenId) {
            this.leistungsbewertungSozialverhaltenId = leistungsbewertungSozialverhaltenId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
    private List<SozialverhaltenNote> sozialverhaltenNoten;

    public List<SozialverhaltenNote> getSozialverhaltenNoten() {
        return sozialverhaltenNoten;
    }

    public void setSozialverhaltenNoten(List<SozialverhaltenNote> sozialverhaltenNoten) {
        this.sozialverhaltenNoten = sozialverhaltenNoten;
    }
}
