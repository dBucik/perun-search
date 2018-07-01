package cz.muni.ics.models;

public class Facility {

    //core
    private Long id;
    private String name;

    //def
    private String afsCell;
    private String afsPartition;
    private String afsServer;
    private String cloudPlatformDestinationMap;
    private String comment;
    private String cpu;
    private String desc;
    private String disk;
    private String displayName;
    private String entityId;
    private String excludeNonValidUsersFromUnixGroups;
    private String fsScratchLocalMountPoint;
    private String fsScratchMountPoint;
    private String googleGroupNameNs;
    private String homeDirUmask;
    private String homeMountPointPasswdScp;
    private String homeMountPoints;
    private String hostCoresNumber;
    private String hostName;
    private String isCluster;
    private String ldapBaseDN;
    private String listingPriority;
    private String loginNs;
    private String memory;
    private String network;
    private String o365DomainName;
    private String o365UsageLocation;
    private String oidcClientId;
    private String owner;
    private String passwdScpDesinationFile;
    private String pbsServer;
    private String pbsMonServer;
    private String photo;
    private String quotaEnabled;
    private String scratchDirPermissions;
    private String scratchLocalDirPermissions;
    private String shellPasswdScp;
    private String shells;
    private String thumbnail;
    private String uidNs;
    private String unixGidNs;
    private String unixGroupNameNs;
    private String url;

    //virt
    private String maxGid;
    private String maxUid;
    private String minGid;
    private String minUid;
}
