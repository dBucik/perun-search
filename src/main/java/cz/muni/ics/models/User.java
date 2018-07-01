package cz.muni.ics.models;

import java.util.List;

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
}
