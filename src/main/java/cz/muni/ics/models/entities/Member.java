package cz.muni.ics.models.entities;

import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Member entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Member extends PerunEntity {

    private Long userId;
    private Long voId;
    private String status;
    private Boolean sponsored;
    private List<PerunAttribute> attributes = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    public List<PerunAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PerunAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Member [" +
                "id: " + getId() +
                ", userId: " + userId +
                ", voId: " + voId +
				", status: " + status +
				", sponsored: " + sponsored +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Member)) {
            return false;
        }

        Member member = (Member) o;
        return super.equals(o) &&
                Objects.equals(userId, member.userId) &&
                Objects.equals(voId, member.voId) &&
				Objects.equals(status, member.status) &&
				Objects.equals(sponsored, member.sponsored);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (userId != null) hash *= 31 * userId.hashCode();
        if (voId != null) hash  *= 31 * voId.hashCode();
        if (status != null) hash *= 31 * status.hashCode();
        if (sponsored != null) hash *= 31 * sponsored.hashCode();

        return hash;
    }

}
