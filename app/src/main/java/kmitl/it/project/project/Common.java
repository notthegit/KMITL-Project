package kmitl.it.project.project;

import kmitl.it.project.project.Remote.RetrofitClient;
import kmitl.it.project.project.Remote.testService;

public class Common {

    private static final String BASE_URL = "https://api.myjson.com/bins/n6qvq/";
    public static testService gettestService()
    {
        return RetrofitClient.getClient(BASE_URL).create(testService.class);
    }
}
