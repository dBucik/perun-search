package cz.muni.ics.models.entities;

import java.util.Objects;

/**
 * ExtSource entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class ExtSource extends PerunEntity {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExtSource [" +
                "id: " + getId() +
                ", name: " + name +
                ", type: " + type +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ExtSource)) {
            return false;
        }

        ExtSource es = (ExtSource) o;
        return super.equals(o) &&
				Objects.equals(this.name, es.name) &&
				Objects.equals(this.type, es.type);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (name != null) hash *= 31 * name.hashCode();
        if (type != null) hash *= 31 * type.hashCode();

        return hash;
    }
}
