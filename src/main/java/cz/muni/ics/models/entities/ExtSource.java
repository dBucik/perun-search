package cz.muni.ics.models.entities;

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

}
