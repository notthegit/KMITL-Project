package kmitl.it.project.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectDetail extends AppCompatActivity {

    public static TextView nameENName, nameTHName, major, stu1Name, stu2Name, year, term;
    public static TextView teach1Name, teach2Name;

    public static String getProject_id() {
        return project_id;
    }

    public static String project_id = "";
    private boolean dbOff = true;
    private boolean noPoster = true;
    public static String id_user = "";
    public static String login_user = "";
    public static String name = "";
    public static boolean staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<SettingApi>> call = api.getSetting();
        call.enqueue(new Callback<List<SettingApi>>() {
            @Override
            public void onResponse(Call<List<SettingApi>> call, retrofit2.Response<List<SettingApi>> response) {
                if (!response.isSuccessful())
                {
//                    test.setText(response.code()); //id 404
                    return;
                }
                List<SettingApi> setting = response.body();
                for (SettingApi setting1 : setting)
                {
                    if (setting1.getActivate() == 1)
                    {
                        dbOff = false;
                    }
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<SettingApi>> call, Throwable t) {
//                test.setText(t.getMessage());
            }
        });

        nameENName = (TextView) findViewById(R.id.nameENName);
        nameTHName = (TextView) findViewById(R.id.nameTHName);
        major = (TextView) findViewById(R.id.major);
        stu1Name = (TextView) findViewById(R.id.stu1Name);
        stu2Name = (TextView) findViewById(R.id.stu2Name);
        year = (TextView) findViewById(R.id.year);
        term = (TextView) findViewById(R.id.term);
        teach1Name = (TextView) findViewById(R.id.teach1Name);
        teach2Name = (TextView) findViewById(R.id.teach2Name);

        if (getIntent().hasExtra("project_id")) {
            project_id = getIntent().getStringExtra("project_id");
        }
        if (getIntent().hasExtra("login_user")) {
            login_user = getIntent().getStringExtra("login_user");
        }
        if (getIntent().hasExtra("id")) {
            id_user = getIntent().getStringExtra("id");
        }
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        }
        if (getIntent().hasExtra("staff")) {
            staff = getIntent().getExtras().getBoolean("staff");
        }

        FetchDetail process = new FetchDetail();
        process.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (staff)
        {
            getMenuInflater().inflate(R.menu.admin_menu, menu);
        }
        else
        {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();

        if (dbOff && staff)
        {
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.setting)
            {
                Intent Menu = new Intent(this,SettingActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
        }
        else if (dbOff)
        {
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
        }
        else if (noPoster && staff)
        {
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,UserScheduleActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
            if(id == R.id.setting)
            {
                Intent Menu = new Intent(this,SettingActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,PosterOffActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
        }
        else if (noPoster)
        {
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,UserScheduleActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,PosterOffActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
        }
        else if (staff)
        {
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,UserScheduleActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,UserPosterActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
            if(id == R.id.setting)
            {
                Intent Menu = new Intent(this,SettingActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
        }
        else
        {
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,UserScheduleActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,UserPosterActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
