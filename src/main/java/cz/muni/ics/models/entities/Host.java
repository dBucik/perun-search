package cz.muni.ics.models.entities;

import java.util.Objects;

/**
 * Host entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Host extends PerunEntity {

    private String description;
    private String hostname;
    private Long facilityId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return "Host [" +
                "id: " + getId() +
                ", description: " + description +
				", hostname: " + hostname +
				", facilityId: " + facilityId +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Host)) {
            return false;
        }

        Host host = (Host) o;
        return super.equals(o) &&
                Objects.equals(description, host.description) &&
				Objects.equals(hostname, host.hostname) &&
				Objects.equals(facilityId, host.facilityId);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (description != null) hash *= 31 * description.hashCode();
        if (hostname != null) hash *= 31 * hostname.hashCode();
        if (facilityId != null) facilityId *= 31 *facilityId.hashCode();

        return hash;
    }

}
