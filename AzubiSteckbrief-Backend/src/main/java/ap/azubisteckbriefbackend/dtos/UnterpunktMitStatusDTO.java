package ap.azubisteckbriefbackend.dtos;

public class UnterpunktMitStatusDTO {
    private Long unterpunktId;
    private String unterpunkt;
    private Boolean status;

    public UnterpunktMitStatusDTO(Long unterpunktId, String unterpunkt, Boolean status) {
        this.unterpunktId = unterpunktId;
        this.unterpunkt = unterpunkt;
        this.status = status;
    }

    public Long getUnterpunktId() {
        return unterpunktId;
    }

    public void setUnterpunktId(Long unterpunktId) {
        this.unterpunktId = unterpunktId;
    }

    public String getUnterpunkt() {
        return unterpunkt;
    }

    public void setUnterpunkt(String unterpunkt) {
        this.unterpunkt = unterpunkt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}