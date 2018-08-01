package cz.muni.ics.models.entities;

/**
 * Group entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Group extends PerunEntity {

    private String name;
    private String description;
    private Long parentGroupId;
    private Long voId;

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

    public Long getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(Long parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    @Override
    public String toString() {
        return "Group [" +
                "id: " + getId() +
                ", name: " + name +
                ", description: " + description +
				", parentGroupId: " + parentGroupId +
				", voId: " + voId +
                "]";
    }

}
