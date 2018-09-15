package cz.muni.ics.models.entities;

import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserExtSource entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class UserExtSource extends PerunEntity {

    private Long userId;
    private String loginExt;
    private Long extSourcesId;
    private Short loa;
    private List<PerunAttribute> attributes = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginExt() {
        return loginExt;
    }

    public void setLoginExt(String loginExt) {
        this.loginExt = loginExt;
    }

    public Long getExtSourcesId() {
        return extSourcesId;
    }

    public void setExtSourcesId(Long extSourcesId) {
        this.extSourcesId = extSourcesId;
    }

    public Short getLoa() {
        return loa;
    }

    public void setLoa(Short loa) {
        this.loa = loa;
    }

	public List<PerunAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<PerunAttribute> attributes) {
		this.attributes = attributes;
	}

    @Override
    public String toString() {
        return "UserExtSource [" +
                "id: " + getId() +
                ", userId: " + userId +
                ", loginExt: " + loginExt +
				", extSourcesId: " + extSourcesId +
				", loa: " + loa +
                "]";
    }

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof UserExtSource)) {
			return false;
		}

		UserExtSource ues = (UserExtSource) o;
		return super.equals(o) &&
				Objects.equals(userId, ues.userId) &&
				Objects.equals(loginExt, ues.loginExt) &&
				Objects.equals(extSourcesId, ues.extSourcesId) &&
				Objects.equals(loa, ues.loa);
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		if (userId != null) hash *= 31 * userId.hashCode();
		if (loginExt != null) hash *= 31 * loginExt.hashCode();
		if (extSourcesId != null) hash *= 31 * extSourcesId.hashCode();
		if (loa != null) hash *= 31 * loa.hashCode();

		return hash;
	}

}
