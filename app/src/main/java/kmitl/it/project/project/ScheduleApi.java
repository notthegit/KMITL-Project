package kmitl.it.project.project;

public class ScheduleApi {

    private String sch;
    private String sch_poster;

    public ScheduleApi(String sch, String sch_poster) {
        this.sch = sch;
        this.sch_poster = sch_poster;
    }

    public String getSch() {
        return sch;
    }

    public String getSch_poster() {
        return sch_poster;
    }
}
