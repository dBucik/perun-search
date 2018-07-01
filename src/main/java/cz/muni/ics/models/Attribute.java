package cz.muni.ics.models;

/**
 * Class represents attribute stored in Perun. Each attribute has key and value.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Attribute {

    private String key;
    private String value;

    public Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Attribute)) return false;
        Attribute ao = (Attribute) o;

        return this.key.equals(ao.key) && this.value.equals(ao.value);
    }

    @Override
    public int hashCode() {
        int res = this.key.hashCode();
        res *= 31 * this.value.hashCode();
        return res;
    }

    @Override
    public String toString() {
        return "{ " + key + ": " + value + "}";
    }
}
