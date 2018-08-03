package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.UserExtSource;

import java.util.List;
import java.util.Objects;

/**
 * UserExtSource entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichUserExtSource extends UserExtSource implements RichPerunEntity {

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
        return super.toString().replaceFirst("UserExtSource", "RichUserExtSource") +
                "{ attributes: " + attributes.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof RichUserExtSource)) {
            return false;
        }

        RichUserExtSource e = (RichUserExtSource) o;
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
