package kmitl.it.project.project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Grade3Activity extends AppCompatActivity {

//    public static TextView g1, g2, g3, g4, g5, g6;
    public TextView g1, g2, g3, g4, g5, g6, name;
    public int s1, s2, s3, s4, s5, s6;
    public String ss1, ss2, ss3, ss4, ss5, ss6;
    int time_spo, character_spo, presentation_spo, question_spo, media_spo;
    int grade_poster_proj_id = 1;
    int quality_spo;
    public static String project_id = "";
    public static String project_name = "";
    private boolean dbOff = true;
//    private boolean noPoster = true;
    private boolean noPoster = false;
    private boolean update = false;
    private boolean connect = true;
    public static String id_user = "";
    public static String login_user = "";
    public static String names = "";
    public static boolean staff;
    public Spinner grade, grade1, grade2, grade3, grade4, grade5;
    ArrayAdapter<String> myAdapter2;

    public static String getProject_id() {
        return project_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade3);

        check();
        grade = (Spinner) findViewById(R.id.textView2Spinner);
        grade1 = (Spinner) findViewById(R.id.textView3Spinner);
        grade2 = (Spinner) findViewById(R.id.textView4Spinner);
        grade3 = (Spinner) findViewById(R.id.textView5Spinner);
        grade4 = (Spinner) findViewById(R.id.textView6Spinner);
        grade5 = (Spinner) findViewById(R.id.textView7Spinner);
        TextView projectName = (TextView) findViewById(R.id.name);
        g1 = (TextView) findViewById(R.id.g1);
        g2 = (TextView) findViewById(R.id.g2);
        g3 = (TextView) findViewById(R.id.g3);
        g4 = (TextView) findViewById(R.id.g4);
        g5 = (TextView) findViewById(R.id.g5);
        g6 = (TextView) findViewById(R.id.g6);
        name = (TextView) findViewById(R.id.name);
        myAdapter2 = new ArrayAdapter<String>(Grade3Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray
                (R.array.grade));
        grade.setAdapter(myAdapter2);
        grade1.setAdapter(myAdapter2);
        grade2.setAdapter(myAdapter2);
        grade3.setAdapter(myAdapter2);
        grade4.setAdapter(myAdapter2);
        grade5.setAdapter(myAdapter2);

        if (getIntent().hasExtra("id")) {
            id_user = getIntent().getStringExtra("id");
        }
        if (getIntent().hasExtra("login_user")) {
            login_user = getIntent().getStringExtra("login_user");
        }
        if (getIntent().hasExtra("project_id")) {
            project_id = getIntent().getStringExtra("project_id");
        }
        if (getIntent().hasExtra("project_name")) {
            project_name = getIntent().getStringExtra("project_name");
            name.setText(project_name);
        }
        if (getIntent().hasExtra("name")) {
            names = getIntent().getStringExtra("name");
        }
        if (getIntent().hasExtra("staff")) {
            staff = getIntent().getExtras().getBoolean("staff");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
//        Call<List<Grade3Api>> call = api.getGrade3(33, getProject_id());
//        Call<List<Grade3Api>> call = api.getGrade3(8, Integer.parseInt(getProject_id()));
        Log.i("test", id_user);
//        Call<List<Grade3Api>> call = api.getGrade3(Integer.parseInt(id_user), Integer.parseInt(getProject_id()));
        Call<List<Grade3Api>> call = api.getGrade3(Integer.parseInt(login_user), Integer.parseInt(getProject_id()));
        Log.i("login_user", login_user);
        Log.i("Project_id", getProject_id()+" t");
        call.enqueue(new Callback<List<Grade3Api>>() {
            @Override
            public void onResponse(Call<List<Grade3Api>> call, Response<List<Grade3Api>> response) {
                if (!response.isSuccessful())
                {
//                    test.setText(response.code()); //id 404
                    g1.setText(response.code()+"");
//                    g1.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g2.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g3.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล ");
                    g4.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g5.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g6.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    connect = false;
                    return;
                }
                List<Grade3Api> grade = response.body();
                for (Grade3Api grades : grade)
                {
                    if (grades.isGrade())
                    {
                        g1.setText("คะแนนเก่า " + grades.getTime_spo());
                        g2.setText("คะแนนเก่า " + grades.getCharacter_spo());
                        g3.setText("คะแนนเก่า " + grades.getPresentation_spo());
                        g4.setText("คะแนนเก่า " + grades.getQuestion_spo());
                        g5.setText("คะแนนเก่า " + grades.getMedia_spo());
                        g6.setText("คะแนนเก่า " + grades.getQuality_spo());
                        grade_poster_proj_id = grades.getId();
                        update = true;
                        s1 = grades.getTime_spo();
                        s2 = grades.getCharacter_spo();
                        s3 = grades.getPresentation_spo();
                        s4 = grades.getQuestion_spo();
                        s5 = grades.getMedia_spo();
                        s6 = grades.getQuality_spo();
                        setSpinner();
                    }
                    else
                    {
                        g1.setText("ยังไม่มีการกรอกคะแนน");
                        g2.setText("ยังไม่มีการกรอกคะแนน");
                        g3.setText("ยังไม่มีการกรอกคะแนน ");
                        g4.setText("ยังไม่มีการกรอกคะแนน");
                        g5.setText("ยังไม่มีการกรอกคะแนน");
                        g6.setText("ยังไม่มีการกรอกคะแนน");
                        grade_poster_proj_id = Integer.parseInt(getProject_id());
                    }

                }
            }


            @Override
            public void onFailure(Call<List<Grade3Api>> call, Throwable t) {
                g1.setText(t.getMessage()+ "");
//                g1.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g2.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g3.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g4.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g5.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g6.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                connect = false;
            }
        });

        grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        time_spo = 0;
                        break;

                    case 1:
                        time_spo = 1;
                        break;

                    case 2:
                        time_spo = 2;
                        break;

                    case 3:
                        time_spo = 3;
                        break;

                    case 4:
                        time_spo = 4;
                        break;

                    case 5:
                        time_spo = 5;
                        break;

                    case 6:
                        time_spo = 6;
                        break;

                    case 7:
                        time_spo = 7;
                        break;

                    case 8:
                        time_spo = 8;
                        break;

                    case 9:
                        time_spo = 9;
                        break;

                    case 10:
                        time_spo = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        character_spo = 0;
                        break;

                    case 1:
                        character_spo = 1;
                        break;

                    case 2:
                        character_spo = 2;
                        break;

                    case 3:
                        character_spo = 3;
                        break;

                    case 4:
                        character_spo = 4;
                        break;

                    case 5:
                        character_spo = 5;
                        break;

                    case 6:
                        character_spo = 6;
                        break;

                    case 7:
                        character_spo = 7;
                        break;

                    case 8:
                        character_spo = 8;
                        break;

                    case 9:
                        character_spo = 9;
                        break;

                    case 10:
                        character_spo = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        presentation_spo = 0;
                        break;

                    case 1:
                        presentation_spo = 1;
                        break;

                    case 2:
                        presentation_spo = 2;
                        break;

                    case 3:
                        presentation_spo = 3;
                        break;

                    case 4:
                        presentation_spo = 4;
                        break;

                    case 5:
                        presentation_spo = 5;
                        break;

                    case 6:
                        presentation_spo = 6;
                        break;

                    case 7:
                        presentation_spo = 7;
                        break;

                    case 8:
                        presentation_spo = 8;
                        break;

                    case 9:
                        presentation_spo = 9;
                        break;

                    case 10:
                        presentation_spo = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        question_spo = 0;
                        break;

                    case 1:
                        question_spo = 1;
                        break;

                    case 2:
                        question_spo = 2;
                        break;

                    case 3:
                        question_spo = 3;
                        break;

                    case 4:
                        question_spo = 4;
                        break;

                    case 5:
                        question_spo = 5;
                        break;

                    case 6:
                        question_spo = 6;
                        break;

                    case 7:
                        question_spo = 7;
                        break;

                    case 8:
                        question_spo = 8;
                        break;

                    case 9:
                        question_spo = 9;
                        break;

                    case 10:
                        question_spo = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        media_spo = 0;
                        break;

                    case 1:
                        media_spo = 1;
                        break;

                    case 2:
                        media_spo = 2;
                        break;

                    case 3:
                        media_spo = 3;
                        break;

                    case 4:
                        media_spo = 4;
                        break;

                    case 5:
                        media_spo = 5;
                        break;

                    case 6:
                        media_spo = 6;
                        break;

                    case 7:
                        media_spo = 7;
                        break;

                    case 8:
                        media_spo = 8;
                        break;

                    case 9:
                        media_spo = 9;
                        break;

                    case 10:
                        media_spo = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        quality_spo = 0;
                        break;

                    case 1:
                        quality_spo = 1;
                        break;

                    case 2:
                        quality_spo = 2;
                        break;

                    case 3:
                        quality_spo = 3;
                        break;

                    case 4:
                        quality_spo = 4;
                        break;

                    case 5:
                        quality_spo = 5;
                        break;

                    case 6:
                        quality_spo = 6;
                        break;

                    case 7:
                        quality_spo = 7;
                        break;

                    case 8:
                        quality_spo = 8;
                        break;

                    case 9:
                        quality_spo = 9;
                        break;

                    case 10:
                        quality_spo = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        Fetch3Grade process = new Fetch3Grade();
//        process.execute();
    }

    public void clickSubmit(View view){

        check();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                sent();
            }
        }, 3000);   //3 seconds

    }

    public void sent()
    {
        if (dbOff || noPoster)
        {
            Toast.makeText(Grade3Activity.this, "ขออภัยตอนนี้ระบบได้ปิดการกรอกคะแนนแล้ว", Toast.LENGTH_LONG).show();
        }
        else if (!connect)
        {
            Toast.makeText(Grade3Activity.this, "ไม่สามารถเชื่อมต่อกับฐานข้อมูลเก่าได้", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (!update)
            {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .saveGradePoster(grade_poster_proj_id, time_spo, character_spo,
                                presentation_spo, question_spo, media_spo, quality_spo,
                                Integer.parseInt(id_user));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "Done";
//                String s = response.body().toString();
                        Toast.makeText(Grade3Activity.this, s, Toast.LENGTH_LONG).show();
                        back();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Grade3Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else
            {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .saveGradePosterPUT(grade_poster_proj_id, time_spo, character_spo, presentation_spo,
                                question_spo, media_spo, quality_spo);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "Done";
//                String s = response.body().toString();
                        Toast.makeText(Grade3Activity.this, s, Toast.LENGTH_LONG).show();
                        back();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Grade3Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        }

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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.setting)
            {
                Intent Menu = new Intent(this,SettingActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,PosterOffActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,UserPosterActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
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
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedulePoster)
            {
                Intent Menu = new Intent(this,UserPosterActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", names);
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

    public void check()
    {
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
                    if (setting1.getForms() == 1)
                    {
                        noPoster = false;
                    }
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<SettingApi>> call, Throwable t) {
//                test.setText(t.getMessage());
            }
        });
    }

    public void back()
    {
        Intent Menu = new Intent(this,UserPosterActivity.class);
        Menu.putExtra("id", id_user);
        Menu.putExtra("name", names);
        Menu.putExtra("staff", staff);
        Menu.putExtra("login_user", login_user);
        startActivity(Menu);
    }
    public void setSpinner()
    {
        ss1 = s1+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss1.equals(myAdapter2.getItem(i)))
            {
                grade.setSelection(i);
                break;
            }
        }
        ss2 = s2+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss2.equals(myAdapter2.getItem(i)))
            {
                grade1.setSelection(i);
                break;
            }
        }
        ss3 = s3+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss3.equals(myAdapter2.getItem(i)))
            {
                grade2.setSelection(i);
                break;
            }
        }
        ss4 = s4+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss4.equals(myAdapter2.getItem(i)))
            {
                grade3.setSelection(i);
                break;
            }
        }
        ss5 = s5+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss5.equals(myAdapter2.getItem(i)))
            {
                grade4.setSelection(i);
                break;
            }
        }
        ss6 = s6+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss6.equals(myAdapter2.getItem(i)))
            {
                grade5.setSelection(i);
                break;
            }
        }
    }
}
