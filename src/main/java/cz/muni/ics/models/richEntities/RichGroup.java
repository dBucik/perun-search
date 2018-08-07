package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Group entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichGroup extends Group implements RichPerunEntity {

    private List<PerunAttribute> attributes = new ArrayList<>();

    @Override
    public List<PerunAttribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    @Override
    public void setAttributes(List<PerunAttribute> attributes) {
        this.attributes = new ArrayList<>(attributes);
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("Group", "RichGroup") +
                "{ attributes: " + attributes + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof RichGroup)) {
            return false;
        }

        RichGroup e = (RichGroup) o;
        return super.equals(o) &&
                Objects.equals(attributes, e.attributes);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (attributes != null) hash *= 31 * attributes.hashCode();

        return hash;
    }

}
