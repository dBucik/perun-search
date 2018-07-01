package cz.muni.ics.models.entities;

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

}
