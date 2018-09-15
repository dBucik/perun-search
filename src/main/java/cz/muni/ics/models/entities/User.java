package cz.muni.ics.models.entities;

import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class User extends PerunEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private String titleBefore;
    private String titleAfter;
    private Boolean serviceAcc;
    private Boolean sponsoredAcc;
    private List<PerunAttribute> attributes = new ArrayList<>();

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

    public Boolean isServiceAcc() {
        return serviceAcc;
    }

    public void setServiceAcc(Boolean serviceAcc) {
        this.serviceAcc = serviceAcc;
    }

    public Boolean isSponsoredAcc() {
        return sponsoredAcc;
    }

    public void setSponsoredAcc(Boolean sponsoredAcc) {
        this.sponsoredAcc = sponsoredAcc;
    }

    public List<PerunAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PerunAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "User [" +
                "id: " + getId() +
                ", titleBefore: " + titleBefore +
                ", firstName: " + firstName +
                ", middleName: " + middleName +
                ", lastName: " + lastName +
                ", titleAfter: " + titleAfter +
                ", serviceAcc: " + serviceAcc +
                ", sponsored: " + sponsoredAcc +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return super.equals(o) &&
                Objects.equals(titleBefore, user.titleAfter) &&
                Objects.equals(firstName, user.titleAfter) &&
				Objects.equals(middleName, user.middleName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(titleAfter, user.titleAfter) &&
				Objects.equals(serviceAcc, user.serviceAcc) &&
				Objects.equals(sponsoredAcc, user.sponsoredAcc);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (titleBefore != null) hash *= 31 * titleBefore.hashCode();
        if (firstName != null)  hash *= 31 * firstName.hashCode();
        if (middleName != null)  hash *= 31 * middleName.hashCode();
        if (lastName != null)  hash *= 31 * lastName.hashCode();
        if (titleAfter != null)  hash *= 31 * titleAfter.hashCode();
        if (serviceAcc != null)  hash *= 31 * serviceAcc.hashCode();
        if (sponsoredAcc != null)  hash *= 31 * sponsoredAcc.hashCode();

        return hash;
    }

}
