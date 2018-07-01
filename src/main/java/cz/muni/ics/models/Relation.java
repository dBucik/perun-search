package cz.muni.ics.models;

import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.entities.Vo;
import cz.muni.ics.models.richEntities.RichExtSource;
import cz.muni.ics.models.richEntities.RichFacility;
import cz.muni.ics.models.richEntities.RichGroup;
import cz.muni.ics.models.richEntities.RichMember;
import cz.muni.ics.models.richEntities.RichResource;
import cz.muni.ics.models.richEntities.RichService;
import cz.muni.ics.models.richEntities.RichUser;
import cz.muni.ics.models.richEntities.RichVo;

import java.security.acl.Owner;
import java.util.List;

public class Relation {

    private PerunEntity primaryEntity;
    private PerunEntity secondaryEntity;
    private RelationType type;
    private List<Attribute> attrs;

    public PerunEntity getPrimaryEntity() {
        return primaryEntity;
    }

    public void setPrimaryEntity(PerunEntity primaryEntity) {
        this.primaryEntity = primaryEntity;
    }

    public PerunEntity getSecondaryEntity() {
        return secondaryEntity;
    }

    public void setSecondaryEntity(PerunEntity secondaryEntity) {
        this.secondaryEntity = secondaryEntity;
    }

    public RelationType getType() {
        return type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }

    public List<Attribute> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attribute> attrs) {
        this.attrs = attrs;
    }

    public Vo getPrimaryAsVo() {
        if (primaryEntity instanceof Vo) {
            return (Vo) primaryEntity;
        }
        return null;
    }

    public RichVo getPrimaryAsRichVo() {
        if (primaryEntity instanceof RichVo) {
            return (RichVo) primaryEntity;
        }
        return null;
    }

    public User getPrimaryAsUser() {
        if (primaryEntity instanceof User) {
            return (User) primaryEntity;
        }
        return null;
    }

    public RichUser getPrimaryAsRichUser() {
        if (primaryEntity instanceof RichUser) {
            return (RichUser) primaryEntity;
        }
        return null;
    }

    public Resource getPrimaryAsResource() {
        if (primaryEntity instanceof Resource) {
            return (Resource) primaryEntity;
        }
        return null;
    }

    public RichResource getPrimaryAsRichResource() {
        if (primaryEntity instanceof RichResource) {
            return (RichResource) primaryEntity;
        }
        return null;
    }

    public Group getPrimaryAsGroup() {
        if (primaryEntity instanceof Group) {
            return (Group) primaryEntity;
        }
        return null;
    }

    public RichGroup getPrimaryAsRichGroup() {
        if (primaryEntity instanceof RichGroup) {
            return (RichGroup) primaryEntity;
        }
        return null;
    }

    public Facility getPrimaryAsFacility() {
        if (primaryEntity instanceof Facility) {
            return (Facility) primaryEntity;
        }
        return null;
    }

    public RichFacility getPrimaryAsRichFacility() {
        if (primaryEntity instanceof RichFacility) {
            return (RichFacility) primaryEntity;
        }
        return null;
    }

    /* GET SECONDARY */

    public ExtSource getSecondaryAsExtSource() {
        return null;
    }

    public RichExtSource getSecondaryAsRichExtSource() {
        return null;
    }

    public Resource getSecondaryAsResource() {
        return null;
    }

    public RichResource getSecondaryAsRichResource() {
        return null;
    }

    public Service getSecondaryAsService() {
        return null;
    }

    public RichService getSecondaryAsRichService() {
        return null;
    }

    public Facility getSecondaryAsFacility() {
        return null;
    }

    public RichFacility getSecondaryAsRichFacility() {
        return null;
    }

    public Group getSecondaryAsGroup() {
        return null;
    }

    public RichGroup getSecondaryAsRichGroup() {
        return null;
    }

    public Member getSecondaryAsMember() {
        return null;
    }

    public RichMember getSecondaryAsRichMember() {
        return null;
    }

    public Owner getSecondaryAsOwner() {
        return null;
    }

}
