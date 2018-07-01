package cz.muni.ics.models;

import java.util.ArrayList;
import java.util.List;

/**
 * UserExtSource entity from Perun.
 * Core attributes are stored in entity and have their get methods.
 * Additional attributes are stored in list accessed with getAttributes method.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class UserExtSource {

    private Long id;
    private Long userId;
    private String loginExt;
    private Long extSourcesId;
    private Short loa;

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

        if (keys.contains("loginExt")) {
            res.add(new Attribute("loginExt", loginExt));
        }

        if (keys.contains("extSourcesId")) {
            res.add(new Attribute("extSourcesId", extSourcesId.toString()));
        }

        if (keys.contains("loa")) {
            res.add(new Attribute("loa", loa.toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }
}
