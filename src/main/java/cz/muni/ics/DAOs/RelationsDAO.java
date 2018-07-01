package cz.muni.ics.DAOs;

import cz.muni.ics.models.Relation;

import java.util.List;

public interface RelationsDAO {

    /* FACILITY_OWNER */
    List<Relation> getFacilityOwnerRelations();

    List<Relation> getFacilityOwnerRelationsForFacility(Long facilityId);

    List<Relation> getFacilityOwnerRelationsForOwner(Long ownerId);

    Relation getFacilityOwnerRelation(Long facilityId, Long ownerId);

    /* GROUP_EXT_SOURCE */

    List<Relation> getGroupExtSourceRelations();

    List<Relation> getGroupExtSourceRelationsForGroup(Long groupId);

    List<Relation> getGroupExtSourceRelationsForExtSource(Long extSourceId);

    Relation getGroupExtSourceRelation(Long groupId, Long extSourceId);

    /* GROUP_GROUP */

    List<Relation> getGroupGroupRelations();

    List<Relation> getGroupGroupRelationsForGroup(Long groupId);

    Relation getGroupGroupRelationForGroups(Long group1Id, Long group2Id);

    /* GROUP_MEMBER */

    List<Relation> getGroupMemberRelations();

    List<Relation> getGroupMemberRelationsForGroup(Long id);

    List<Relation> getGroupMemberRelationsForMember(Long memberId);

    Relation getGroupMemberRelation(Long groupId, Long memberId);

    /* GROUP_RESOURCE */

    List<Relation> getGroupResourceRelations();

    List<Relation> getGroupResourceRelationsForGroup(Long id);

    List<Relation> getGroupResourceRelationsForResource(Long resourceId);

    Relation getGroupResourceRelation(Long groupId, Long resourceId);

    /* MEMBER_GROUP */

    List<Relation> getMemberGroupRelations();

    List<Relation> getMemberGroupRelationsForMember(Long memberId);

    List<Relation> getMemberGroupRelationsForGroup(Long groupId);

    Relation getMemberGroupRelation(Long memberId, Long groupId);

    /* MEMBER_RESOURCE */

    List<Relation> getMemberResourceRelations();

    List<Relation> getMemberResourceRelationsForMember(Long memberId);

    List<Relation> getMemberResourceRelationsForResource(Long resourceId);

    Relation getMemberResourceRelation(Long memberId, Long resourceId);

    /* RESOURCE_SERVICE */

    List<Relation> getResourceServiceRelations();

    List<Relation> getResourceServiceRelationsForResource(Long resourceId);

    List<Relation> getResourceServiceRelationsForService(Long serviceId);

    Relation getResourceServiceRelation(Long memberId, Long serviceId);

    /* USER_FACILITY */

    List<Relation> getUserFacilityRelations();

    List<Relation> getUserFacilityRelationsForUser(Long userId);

    List<Relation> getUserFacilityRelationsForFacility(Long facilityId);

    Relation getUserFacilityRelation();

    /* VO_EXT_SOURCE */

    List<Relation> getVoExtSourceRelations();

    List<Relation> getVoExtSourceRelationsForVo(Long voId);

    List<Relation> getVoExtSourceRelationsForExtSource(Long extSourceId);

    Relation getVoExtSourceRelation(Long voId, Long extSourceId);

}
