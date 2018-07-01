package cz.muni.ics.models;

public class Host {

    //core
    private Long id;
    private String hostname;

    //def
    private String coresNumber;
    private String frontend;
    private String reserved;
    private String sshHostKey;

    //opt
    private String metaIsManaged;
    private String metaIsMonitored;

    //virt
    private String loa;
}
