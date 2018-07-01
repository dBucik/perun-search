package cz.muni.ics.models;

public class Resource {

    //core
    private Long id;
    private String name;
    private String description;
    private Long voId;

    //def
    private String afsDefaultUsersQuota;
    private String afsDefaultUsersRealm;
    private String afsUsersMountPoint;
    private String afsUsersVolume;
    private String afsVolume;
    private String apacheAuthzFile;
    private String apacheSSLAuthzDir;
    private String appDBContactRole;
    private String defaultDataLimit;
    private String defaultDataQuota;
    private String defaultDataQuotas;
    private String defaultFileLimit;
    private String defaultFileQuotas;
    private String defaultHomeMountPoint;
    private String defaultShell;
    private String fairshareGroupName;
    private String fsHomeMountPoint;
    private String fsVolume;
    private String homeMountPoints;
    private String isReplicaSourcePathStandaloneDir;
    private String k5LoginTargetUser;
    private String mailAliasesTargetUser;
    private String mailingListAllowInvalidUsers;
    private String mailingListManagerMail;
    private String mailingListName;
    private String mailingListUsesLangVariants;
    private String ownCloudAdminResource;
    private String projectsBasePath;
    private String quotaFileSystem;
    private String replicaDestination;
    private String replicatDestinationPath;
    private String replicaSourcePath;
    private String shells;
    private String unixGidNsCerit;
    private String unixGidNsEgiUi;
    private String unixGidNsEinfra;
    private String unixGidNsNcbr;
    private String unixGidNsSagrid;
    private String unixGidNsSitola;
    private String unixGidNsStorage;
    private String unixGidNsUlozisteMu;
    private String unixGroupNameNsCerit;
    private String unixGroupNameNsEgiUi;
    private String unixGroupNameNsEinfra;
    private String unixGroupNameNsMetacloud;
    private String unixGroupNameNsNcbr;
    private String unixGroupNameNsSagrid;
    private String unixGroupNameNsSitola;
    private String unixGroupNameNsStorage;
    private String unixGroupNameNsUlozisteMu;
    private String userSettingsDescription;
    private String vomsRules;
    private String maxUserDataQuotas;
    private String maxUserFileQuotas;
    private String o365AssignedLicense;
    private String openNebulaGroupName;
    private String sshKeysTargetUser;
    private String openNebudeRoles;
    private String firewallRules;
    private String userSettingsName;

    //virt
    private String unixGid;
    private String unixGroupName;
    private String voShortName;
}
