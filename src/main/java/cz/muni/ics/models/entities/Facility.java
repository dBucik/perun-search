package cz.muni.ics.models.entities;

import java.util.Objects;

/**
 * Facility entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Facility extends PerunEntity {

    private String name;
    private String description;

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

    @Override
    public String toString() {
        return "Facility [" +
                "id: " + getId() +
                ", name: " + name +
                ", description: " + description +
                "]";
    }

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Facility)) {
			return false;
		}

		Facility facility = (Facility) o;
		return super.equals(o) &&
				Objects.equals(name, facility.name) &&
				Objects.equals(description, facility.description);
	}

	@Override
	public int hashCode() {
    	int hash = super.hashCode();
    	if (name != null) hash *= 31 * name.hashCode();
    	if (description != null) hash *= 31 * description.hashCode();

    	return hash;
	}
}
