package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Resource;

import java.util.List;
import java.util.Objects;

/**
 * Resource entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichResource extends Resource implements RichPerunEntity {

    private List<PerunAttribute> attributes;

    @Override
    public List<PerunAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(List<PerunAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("Resource", "RichResource") +
                "{ attributes: " + attributes.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof RichResource)) {
            return false;
        }

        RichResource e = (RichResource) o;
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
