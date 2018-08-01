package cz.muni.ics.models.entities;

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

}