package cz.muni.ics.models.entities;

import java.util.Objects;

public class Owner extends PerunEntity {

    private String name;
    private String contact;
    private String status;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "Owner [" +
				"id: " + getId() +
				", name: " + name +
				", contact: " + contact +
				", status: " + status +
				", type: " + type +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Owner)) {
			return false;
		}

		Owner owner = (Owner) o;
		return super.equals(o) &&
				Objects.equals(name, owner.name) &&
				Objects.equals(contact, owner.contact) &&
				Objects.equals(status, owner.status) &&
				Objects.equals(type, owner.type);
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		if (name != null) hash *= 31 * name.hashCode();
		if (contact != null) hash *= 31 * contact.hashCode();
		if (status != null) hash *= 31 * status.hashCode();
		if (type != null) hash *= 31 * type.hashCode();

		return hash;
	}

}
