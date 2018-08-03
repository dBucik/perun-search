package cz.muni.ics.models.entities;

import java.util.Objects;

/**
 * Vo entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Vo extends PerunEntity {

    private String name;
    private String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "VO [" +
                "id: " + getId() +
                ", name: " + name +
                ", shortName: " + shortName +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Vo)) {
            return false;
        }

        Vo vo = (Vo) o;
        return super.equals(o) &&
                Objects.equals(name, vo.name) &&
                Objects.equals(shortName, vo.shortName);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (name != null) hash *= 31 * name.hashCode();
        if (shortName != null)  hash *= 31 * shortName.hashCode();

        return hash;
    }

}