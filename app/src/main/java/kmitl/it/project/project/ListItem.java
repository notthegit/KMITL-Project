package kmitl.it.project.project;

public class ListItem {
    private String schDate;
    private String schTime;
    private String schRoom;
    private String schName;
    private String schProId;
    private String schAdvisor;
    private String schCoAdvisor;
    private String id_user;
    private String login_user;
    private String name;
    private boolean staff;

    public String getSchAdvisor() {
        return schAdvisor;
    }

    public String getSchCoAdvisor() {
        return schCoAdvisor;
    }

    public String getSchProId() {
        return schProId;
    }

    public String getSchDate() {
        return schDate;
    }

    public String getSchTime() {
        return schTime;
    }

    public String getSchRoom() {
        return schRoom;
    }

    public String getSchName() {
        return schName;
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

    public ListItem(String schDate, String schTime, String schRoom, String schName, String schProId, String schAdvisor, String schCoAdvisor, String id_user, String login_user,String name, boolean staff) {
        this.schDate = schDate;
        this.schTime = schTime;
        this.schRoom = schRoom;
        this.schName = schName;
        this.schProId = schProId;
        this.schAdvisor = schAdvisor;
        this.schCoAdvisor = schCoAdvisor;
        this.id_user = id_user;
        this.login_user = login_user;
        this.name = name;
        this.staff = staff;
    }
}
