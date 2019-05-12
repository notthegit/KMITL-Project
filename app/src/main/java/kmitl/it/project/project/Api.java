package kmitl.it.project.project;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("savegrade")
    Call<ResponseBody> saveGrade(
            @Field("grade_proj_id") int grade_proj_id,
            @Field("presentation") int presentation,
            @Field("question") int question,
            @Field("report") int report,
            @Field("presentation_media") int presentation_media,
            @Field("discover") int discover,
            @Field("analysis") int analysis,
            @Field("quantity") int quantity,
            @Field("levels") int levels,
            @Field("quality") int quality
    );

    @FormUrlEncoded
    @PUT("savegrade")
    Call<ResponseBody> saveGradePUT(
            @Field("grade_proj_id") int grade_proj_id,
            @Field("presentation") int presentation,
            @Field("question") int question,
            @Field("report") int report,
            @Field("presentation_media") int presentation_media,
            @Field("discover") int discover,
            @Field("analysis") int analysis,
            @Field("quantity") int quantity,
            @Field("levels") int levels,
            @Field("quality") int quality
    );

    @FormUrlEncoded
    @POST("savegradeadvisor")
    Call<ResponseBody> saveGradeAdvisor(
            @Field("grade_advisor_proj_id") int grade_advisor_proj_id,
            @Field("propose") int propose,
            @Field("planning") int planning,
            @Field("tool") int tool,
            @Field("advice") int advice,
            @Field("improve") int improve,
            @Field("quality_report") int quality_report,
            @Field("quality_project") int quality_project
    );

    @FormUrlEncoded
    @PUT("savegradeadvisor")
    Call<ResponseBody> saveGradeAdvisorPUT(
            @Field("grade_advisor_proj_id") int grade_advisor_proj_id,
            @Field("propose") int propose,
            @Field("planning") int planning,
            @Field("tool") int tool,
            @Field("advice") int advice,
            @Field("improve") int improve,
            @Field("quality_report") int quality_report,
            @Field("quality_project") int quality_project
    );

    @FormUrlEncoded
    @POST("savegradeposter")
    Call<ResponseBody> saveGradePoster(
            @Field("grade_poster_proj_id") int grade_poster_proj_id,
            @Field("time_spo") int time_spo,
            @Field("character_spo") int character_spo,
            @Field("presentation_spo") int presentation_spo,
            @Field("question_spo") int question_spo,
            @Field("media_spo") int media_spo,
            @Field("quality_spo") int quality_spo
    );

    @FormUrlEncoded
    @PUT("savegradeposter")
    Call<ResponseBody> saveGradePosterPUT(
            @Field("grade_poster_proj_id") int grade_poster_proj_id,
            @Field("time_spo") int time_spo,
            @Field("character_spo") int character_spo,
            @Field("presentation_spo") int presentation_spo,
            @Field("question_spo") int question_spo,
            @Field("media_spo") int media_spo,
            @Field("quality_spo") int quality_spo
    );

    @FormUrlEncoded
    @POST("login/")
    Call<LoginApi> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("settingadmin")
    Call<ResponseBody> settingAdmin(
            @Field("activate") int activate,
            @Field("forms") int forms);

    @GET("setting")
    Call<List<SettingApi>> getSetting();

    @GET("allschedule")
    Call<ScheduleApi> getSchedule(
            @Query("login_user_id") int input_login_user_id
    );

    @GET("grade")
    Call<List<GradeApi>> getGrade(
            @Query("login_user_id") int input_login_user_id,
            @Query("score_proj_id") int input_score_proj_id);

    @GET("gradeadvisor")
    Call<List<Grade2Api>> getGrade2(
            @Query("login_user_id") int input_login_user_id,
            @Query("score_advisor_id") int input_score_advisor_id);

    @GET("gradeposter")
    Call<List<Grade3Api>> getGrade3(
            @Query("login_user_id") int input_login_user_id,
            @Query("score_poster_id") int input_score_poster_id);
}
