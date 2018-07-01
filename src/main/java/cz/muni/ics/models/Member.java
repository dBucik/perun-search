package cz.muni.ics.models;

import java.util.List;

public class Member {

    //core
    private Long id;
    private Long userId;
    private Long voId;
    private String status;
    private Boolean sponsored;

    private List<Attribute> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
