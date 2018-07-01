package cz.muni.ics.models.entities;

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

}
