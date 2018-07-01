package cz.muni.ics.models;

import org.json.JSONObject;

public class UserExtSource {

    private Long id;
    private Long userId;
    private String loginExt;
    private Long extSourcesId;
    private Short loa;

    private JSONObject attributes;

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

    public String getLoginExt() {
        return loginExt;
    }

    public void setLoginExt(String loginExt) {
        this.loginExt = loginExt;
    }

    public Long getExtSourcesId() {
        return extSourcesId;
    }

    public void setExtSourcesId(Long extSourcesId) {
        this.extSourcesId = extSourcesId;
    }

    public Short getLoa() {
        return loa;
    }

    public void setLoa(Short loa) {
        this.loa = loa;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }
}
