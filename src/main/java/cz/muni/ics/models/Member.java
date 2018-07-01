package cz.muni.ics.models;

import java.util.ArrayList;
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

    public List<Attribute> getAttributesByKeys(List<String> keys) {
        List<Attribute> res = new ArrayList<>();

        if (keys.contains("id")) {
            res.add(new Attribute("id", id.toString()));
        }

        if (keys.contains("userId")) {
            res.add(new Attribute("userId", userId.toString()));
        }

        if (keys.contains("voId")) {
            res.add(new Attribute("voId", voId.toString()));
        }

        if (keys.contains("status")) {
            res.add(new Attribute("status", status));
        }

        if (keys.contains("sponsored")) {
            res.add(new Attribute("sponsored", sponsored.toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }
}
