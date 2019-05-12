package kmitl.it.project.project;

public class ListResult {

    private String resultName;
    private String id;
    private String all_grade;
    private String all_grade1;
    private String all_grade2;
    private String all_grade3;
    private String id_user;
    private String login_user;
    private String name;
    private boolean staff;

    public String getResultName() {
        return resultName;
    }

    public String getId() {
        return id;
    }

    public String getAll_grade() {
        return all_grade;
    }

    public String getAll_grade1() {
        return all_grade1;
    }

    public String getAll_grade2() {
        return all_grade2;
    }

    public String getAll_grade3() {
        return all_grade3;
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

    public ListResult(String resultName, String id, String all_grade, String all_grade1, String all_grade2, String all_grade3, String id_user, String login_user,String name, boolean staff) {
        this.resultName = resultName;
        this.id = id;
        this.all_grade = all_grade;
        this.all_grade1 = all_grade1;
        this.all_grade2 = all_grade2;
        this.all_grade3 = all_grade3;
        this.id_user = id_user;
        this.login_user = login_user;
        this.name = name;
        this.staff = staff;
    }
}
