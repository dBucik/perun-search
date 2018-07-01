package cz.muni.ics.models.entities;

/**
 * Resource entity from Perun.
 * Core attributes are stored in entity and have their get methods.
 * Additional attributes are stored in list accessed with getAttributes method.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Resource {

    private Long id;
    private Long facilityId;
    private String name;
    private String description;
    private Long voId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}
