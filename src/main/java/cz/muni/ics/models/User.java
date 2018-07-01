package cz.muni.ics.models;

import java.util.ArrayList;
import java.util.List;

/**
 * User entity from Perun.
 * Core attributes are stored in entity and have their get methods.
 * Additional attributes are stored in list accessed with getAttributes method.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class User {

    //core
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String titleBefore;
    private String titleAfter;
    private Boolean serviceAcc;
    private Boolean sponsoredAcc;

    private List<Attribute> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTitleBefore() {
        return titleBefore;
    }

    public void setTitleBefore(String titleBefore) {
        this.titleBefore = titleBefore;
    }

    public String getTitleAfter() {
        return titleAfter;
    }

    public void setTitleAfter(String titleAfter) {
        this.titleAfter = titleAfter;
    }

    public Boolean getServiceAcc() {
        return serviceAcc;
    }

    public void setServiceAcc(Boolean serviceAcc) {
        this.serviceAcc = serviceAcc;
    }

    public Boolean getSponsoredAcc() {
        return sponsoredAcc;
    }

    public void setSponsoredAcc(Boolean sponsoredAcc) {
        this.sponsoredAcc = sponsoredAcc;
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

        if (keys.contains("firstName")) {
            res.add(new Attribute("firstName", firstName));
        }

        if (keys.contains("middleName")) {
            res.add(new Attribute("middleName", middleName));
        }

        if (keys.contains("lastName")) {
            res.add(new Attribute("lastName", lastName));
        }

        if (keys.contains("titleBefore")) {
            res.add(new Attribute("titleBefore", titleBefore));
        }

        if (keys.contains("titleAfter")) {
            res.add(new Attribute("titleAfter", titleAfter));
        }

        if (keys.contains("serviceAcc")) {
            res.add(new Attribute("serviceAcc", serviceAcc.toString()));
        }

        if (keys.contains("sponsoredAcc")) {
            res.add(new Attribute("sponsoredAcc", sponsoredAcc.toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }
}
