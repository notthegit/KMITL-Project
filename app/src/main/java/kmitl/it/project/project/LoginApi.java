package kmitl.it.project.project;

public class LoginApi {

    private String result;
    private String username;
    private String id;
    private String login_user;
    private String teacher_name;
    private boolean superuser;
    private boolean staff;

    public LoginApi(String result, String username, String id, String login_user,String teacher_name, boolean superuser, boolean staff) {
        this.result = result;
        this.username = username;
        this.id = id;
        this.login_user = login_user;
        this.teacher_name = teacher_name;
        this.superuser = superuser;
        this.staff = staff;
    }

    public String getUsername() {
        return username;
    }

    public String getResult() {
        return result;
    }

    public String getId() {
        return id;
    }

    public String getLogin_user() {
        return login_user;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public boolean isStaff() {
        return staff;
    }
}
