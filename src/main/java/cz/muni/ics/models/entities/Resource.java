package cz.muni.ics.models.entities;

/**
 * Resource entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Resource extends PerunEntity {

    private Long facilityId;
    private String name;
    private String description;
    private Long voId;

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }

    @Override
    public String toString() {
        return "Resource [" +
                "id: " + getId() +
                ", name: " + name +
                ", description: " + description +
				", voId: " + voId +
                "]";
    }

}
