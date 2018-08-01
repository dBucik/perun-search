package cz.muni.ics.models.entities;

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

}
