package kmitl.it.project.project.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import kmitl.it.project.project.Model.apitest;

public interface testService {
    @GET("")
    Call<apitest> getApi1();
}
