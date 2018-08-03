package cz.muni.ics.models.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Group)) {
            return false;
        }

        Group group = (Group) o;
        return super.equals(o) &&
                Objects.equals(name, group.name) &&
                Objects.equals(description, group.description) &&
				Objects.equals(parentGroupId, group.parentGroupId) &&
				Objects.equals(voId, group.voId);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (name != null) hash *= 31 * name.hashCode();
        if (description != null) hash *= 31 * description.hashCode();
        if (parentGroupId != null) hash *= 31 * parentGroupId.hashCode();
        if (voId != null) hash *= 31 * voId.hashCode();

        return hash;
    }

}
