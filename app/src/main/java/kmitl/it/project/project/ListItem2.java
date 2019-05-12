package kmitl.it.project.project;

public class ListItem2 {

    private String schPosterDate;
    private String schPosterName;
    private String schPosterProId;
    private String id_user;
    private String login_user;
    private String name;
    private boolean staff;

    public String getSchPosterProId() {
        return schPosterProId;
    }

    public String getSchPosterDate() {
        return schPosterDate;
    }

    public String getSchPosterName() {
        return schPosterName;
    }

    public String getId_user() {
        return id_user;
    }

    public String getLogin_user() {
        return login_user;
    }

    public String getName() {
        return name;
    }

    public boolean isStaff() {
        return staff;
    }

    public ListItem2(String schPosterDate, String schPosterName, String schPosterProId, String id_user, String login_user,String name, boolean staff) {
        this.schPosterDate = schPosterDate;
        this.schPosterName = schPosterName;
        this.schPosterProId = schPosterProId;
        this.id_user = id_user;
        this.login_user = login_user;
        this.name = name;
        this.staff = staff;
    }
}
