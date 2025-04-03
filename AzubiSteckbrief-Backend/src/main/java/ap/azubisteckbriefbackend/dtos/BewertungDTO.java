package ap.azubisteckbriefbackend.dtos;

import java.util.List;

public class BewertungDTO {
    public Long azubiId;
    public Long referatId;
    public Long schulungId;
    public List<ErledigterUnterpunktDTO> erledigtePunkte;

    public static class ErledigterUnterpunktDTO {
        public Long unterpunktId;
        public Boolean status;
    }
}
