package cz.muni.ics.models.entities;

import java.util.Objects;

/**
 * Basic perun entity. Specific entities extend this class
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class PerunEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PerunEntity [" +
                "id: " + getId() +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof  PerunEntity)) {
            return false;
        }

        PerunEntity e = (PerunEntity) o;
        return Objects.equals(this.id, e.id);
    }

	@Override
	public int hashCode() {
		int hash = 1;
		if (id != null) hash *= 31 * id.hashCode();
		return hash;
	}
}