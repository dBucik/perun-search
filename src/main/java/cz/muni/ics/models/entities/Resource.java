package cz.muni.ics.models.entities;

import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<PerunAttribute> attributes = new ArrayList<>();

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

	public List<PerunAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<PerunAttribute> attributes) {
		this.attributes = attributes;
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

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Resource)) {
			return false;
		}

		Resource resource = (Resource) o;
		return super.equals(o) &&
				Objects.equals(name, resource.name) &&
				Objects.equals(description, resource.description) &&
				Objects.equals(voId, resource.voId);
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		if (name != null) hash *= 31 * name.hashCode();
		if (description != null) hash *= 31 * description.hashCode();
		if (voId != null) hash *= 31 * voId.hashCode();

		return hash;
	}

}
