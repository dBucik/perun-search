package cz.muni.ics.models.entities;

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
}
