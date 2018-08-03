package cz.muni.ics.models.entities;

import java.util.Objects;

/**
 * Service entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Service extends PerunEntity {

    private String name;
    private Long ownerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Service [" +
                "id: " + getId() +
                ", name: " + name +
                ", ownerId: " + ownerId +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Service)) {
            return false;
        }

        Service service = (Service) o;
        return super.equals(o) &&
                Objects.equals(name, service.name) &&
                Objects.equals(ownerId, service.ownerId);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (name != null) hash *= 31 * name.hashCode();
        if (ownerId != null) hash *= 31 * ownerId.hashCode();

        return hash;
    }

}
