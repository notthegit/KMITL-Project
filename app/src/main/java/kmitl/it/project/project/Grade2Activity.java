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

public class Grade2Activity extends AppCompatActivity {

    public TextView g1, g2, g3, g4, g5, g6, g7, name;
    public int s1, s2, s3, s4, s5, s6, s7;
    public String ss1, ss2, ss3, ss4, ss5, ss6, ss7;
    int propose, planning, tool, advice, improve, quality_report;
    int grade_advisor_proj_id = 1;
    int quality_project;
    private boolean dbOff = true;
    private boolean noPoster = true;
    private boolean update = false;
    private boolean connect = true;
    public static String id_user = "";
    public static String project_name = "";
    public static String project_id = "";
    public static String names = "";
    public static String login_user = "";
    public static boolean staff;
    public Spinner grade, grade1, grade2, grade3, grade4, grade5, grade6;
    ArrayAdapter<String> myAdapter2;

    public static String getProject_id() {
        return project_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade2);
        grade = (Spinner) findViewById(R.id.textView2Spinner);
        grade1 = (Spinner) findViewById(R.id.textView3Spinner);
        grade2 = (Spinner) findViewById(R.id.textView4Spinner);
        grade3 = (Spinner) findViewById(R.id.textView5Spinner);
        grade4 = (Spinner) findViewById(R.id.textView6Spinner);
        grade5 = (Spinner) findViewById(R.id.textView7Spinner);
        grade6 = (Spinner) findViewById(R.id.textView8Spinner);
        TextView projectName = (TextView) findViewById(R.id.name);
        myAdapter2 = new ArrayAdapter<String>(Grade2Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray
                (R.array.grade));
        grade.setAdapter(myAdapter2);
        grade1.setAdapter(myAdapter2);
        grade2.setAdapter(myAdapter2);
        grade3.setAdapter(myAdapter2);
        grade4.setAdapter(myAdapter2);
        grade5.setAdapter(myAdapter2);
        grade6.setAdapter(myAdapter2);
        g1 = (TextView) findViewById(R.id.g1);
        g2 = (TextView) findViewById(R.id.g2);
        g3 = (TextView) findViewById(R.id.g3);
        g4 = (TextView) findViewById(R.id.g4);
        g5 = (TextView) findViewById(R.id.g5);
        g6 = (TextView) findViewById(R.id.g6);
        g7 = (TextView) findViewById(R.id.g7);
        name = (TextView) findViewById(R.id.name);

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
        Log.i("test", id_user);
        Call<List<Grade2Api>> call = api.getGrade2(Integer.parseInt(login_user), Integer.parseInt(getProject_id()));
        Log.i("login_user", login_user);
        Log.i("Project_id", getProject_id()+" t");
        call.enqueue(new Callback<List<Grade2Api>>() {
            @Override
            public void onResponse(Call<List<Grade2Api>> call, Response<List<Grade2Api>> response) {
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
                    g7.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    connect = false;
                    return;
                }
                List<Grade2Api> grade = response.body();
                for (Grade2Api grades : grade)
                {
                    if (grades.isGrade())
                    {
                        g1.setText("คะแนนเก่า " + grades.getPropose());
                        g2.setText("คะแนนเก่า " + grades.getPlanning());
                        g3.setText("คะแนนเก่า " + grades.getTool());
                        g4.setText("คะแนนเก่า " + grades.getAdvice());
                        g5.setText("คะแนนเก่า " + grades.getImprove());
                        g6.setText("คะแนนเก่า " + grades.getQuality_report());
                        g7.setText("คะแนนเก่า " + grades.getQuality_project());
                        grade_advisor_proj_id = grades.getId();
                        update = true;
                        s1 = grades.getPropose();
                        s2 = grades.getPlanning();
                        s3 = grades.getTool();
                        s4 = grades.getAdvice();
                        s5 = grades.getImprove();
                        s6 = grades.getQuality_report();
                        s7 = grades.getQuality_project();
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
                        g7.setText("ยังไม่มีการกรอกคะแนน");
                        grade_advisor_proj_id = Integer.parseInt(getProject_id());
                    }

                }
            }


            @Override
            public void onFailure(Call<List<Grade2Api>> call, Throwable t) {
                g1.setText(t.getMessage()+ "");
                Log.i("error", t.getMessage()+ "");
//                g1.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g2.setText("id_user "+id_user);
                g3.setText("project_id "+project_id);
//                g2.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
//                g3.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g4.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g5.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g6.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g7.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                connect = false;
            }
        });

        grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        propose = 0;
                        break;

                    case 1:
                        propose = 1;
                        break;

                    case 2:
                        propose = 2;
                        break;

                    case 3:
                        propose = 3;
                        break;

                    case 4:
                        propose = 4;
                        break;

                    case 5:
                        propose = 5;
                        break;

                    case 6:
                        propose = 6;
                        break;

                    case 7:
                        propose = 7;
                        break;

                    case 8:
                        propose = 8;
                        break;

                    case 9:
                        propose = 9;
                        break;

                    case 10:
                        propose = 10;
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
                        planning = 0;
                        break;

                    case 1:
                        planning = 1;
                        break;

                    case 2:
                        planning = 2;
                        break;

                    case 3:
                        planning = 3;
                        break;

                    case 4:
                        planning = 4;
                        break;

                    case 5:
                        planning = 5;
                        break;

                    case 6:
                        planning = 6;
                        break;

                    case 7:
                        planning = 7;
                        break;

                    case 8:
                        planning = 8;
                        break;

                    case 9:
                        planning = 9;
                        break;

                    case 10:
                        planning = 10;
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
                        tool = 0;
                        break;

                    case 1:
                        tool = 1;
                        break;

                    case 2:
                        tool = 2;
                        break;

                    case 3:
                        tool = 3;
                        break;

                    case 4:
                        tool = 4;
                        break;

                    case 5:
                        tool = 5;
                        break;

                    case 6:
                        tool = 6;
                        break;

                    case 7:
                        tool = 7;
                        break;

                    case 8:
                        tool = 8;
                        break;

                    case 9:
                        tool = 9;
                        break;

                    case 10:
                        tool = 10;
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
                        advice = 0;
                        break;

                    case 1:
                        advice = 1;
                        break;

                    case 2:
                        advice = 2;
                        break;

                    case 3:
                        advice = 3;
                        break;

                    case 4:
                        advice = 4;
                        break;

                    case 5:
                        advice = 5;
                        break;

                    case 6:
                        advice = 6;
                        break;

                    case 7:
                        advice = 7;
                        break;

                    case 8:
                        advice = 8;
                        break;

                    case 9:
                        advice = 9;
                        break;

                    case 10:
                        advice = 10;
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
                        improve = 0;
                        break;

                    case 1:
                        improve = 1;
                        break;

                    case 2:
                        improve = 2;
                        break;

                    case 3:
                        improve = 3;
                        break;

                    case 4:
                        improve = 4;
                        break;

                    case 5:
                        improve = 5;
                        break;

                    case 6:
                        improve = 6;
                        break;

                    case 7:
                        improve = 7;
                        break;

                    case 8:
                        improve = 8;
                        break;

                    case 9:
                        improve = 9;
                        break;

                    case 10:
                        improve = 10;
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
                        quality_report = 0;
                        break;

                    case 1:
                        quality_report = 1;
                        break;

                    case 2:
                        quality_report = 2;
                        break;

                    case 3:
                        quality_report = 3;
                        break;

                    case 4:
                        quality_report = 4;
                        break;

                    case 5:
                        quality_report = 5;
                        break;

                    case 6:
                        quality_report = 6;
                        break;

                    case 7:
                        quality_report = 7;
                        break;

                    case 8:
                        quality_report = 8;
                        break;

                    case 9:
                        quality_report = 9;
                        break;

                    case 10:
                        quality_report = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        quality_project = 0;
                        break;

                    case 1:
                        quality_project = 1;
                        break;

                    case 2:
                        quality_project = 2;
                        break;

                    case 3:
                        quality_project = 3;
                        break;

                    case 4:
                        quality_project = 4;
                        break;

                    case 5:
                        quality_project = 5;
                        break;

                    case 6:
                        quality_project = 6;
                        break;

                    case 7:
                        quality_project = 7;
                        break;

                    case 8:
                        quality_project = 8;
                        break;

                    case 9:
                        quality_project = 9;
                        break;

                    case 10:
                        quality_project = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

//        grade_advisor_proj_id = 2247;
//        Call<ResponseBody> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .saveGradeAdvisor(grade_advisor_proj_id, propose, planning, tool,
//                        advice, improve, quality_report, quality_project);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String s = "Done";
////                String s = response.body().toString();
//                Toast.makeText(Grade2Activity.this, s, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(Grade2Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
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

    public void sent()
    {
        if (dbOff)
        {
            Toast.makeText(Grade2Activity.this, "ขออภัยตอนนี้ระบบได้ปิดการกรอกคะแนนแล้ว", Toast.LENGTH_LONG).show();
        }
        else if (!connect)
        {
            Toast.makeText(Grade2Activity.this, "ไม่สามารถเชื่อมต่อกับฐานข้อมูลเก่าได้", Toast.LENGTH_LONG).show();
        }
        else
        {
//            grade_advisor_proj_id = 2247;
            if (!update)
            {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .saveGradeAdvisor(grade_advisor_proj_id, propose, planning, tool,
                                advice, improve, quality_report, quality_project,
                                Integer.parseInt(id_user));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "Done";
//                String s = response.body().toString();
                        Toast.makeText(Grade2Activity.this, s, Toast.LENGTH_LONG).show();
                        Log.i("grade_advisor_proj_id", grade_advisor_proj_id+"");
                        Log.i("propose", propose+"");
                        Log.i("planning", planning+"");
                        Log.i("tool", tool+"");
                        Log.i("advice", advice+"");
                        Log.i("improve", improve+"");
                        Log.i("quality_report", quality_report+"");
                        Log.i("quality_project", quality_project+"");
                        back();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Grade2Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("grade_advisor_proj_id", grade_advisor_proj_id+"");
                        Log.i("propose", propose+"");
                        Log.i("planning", planning+"");
                        Log.i("tool", tool+"");
                        Log.i("advice", advice+"");
                        Log.i("improve", improve+"");
                        Log.i("quality_report", quality_report+"");
                        Log.i("quality_project", quality_project+"");
                        Log.i("getMessage", t.getMessage()+"");
                    }
                });
            }
            else
            {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .saveGradeAdvisorPUT(grade_advisor_proj_id, propose, planning, tool,
                                advice, improve, quality_report, quality_project);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "Done";
//                String s = response.body().toString();
                        Toast.makeText(Grade2Activity.this, s, Toast.LENGTH_LONG).show();
                        Log.i("grade_advisor_proj_id", grade_advisor_proj_id+"");
                        Log.i("propose", propose+"");
                        Log.i("planning", planning+"");
                        Log.i("tool", tool+"");
                        Log.i("advice", advice+"");
                        Log.i("improve", improve+"");
                        Log.i("quality_report", quality_report+"");
                        Log.i("quality_project", quality_project+"");
                        back();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Grade2Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("grade_advisor_proj_id", grade_advisor_proj_id+"");
                        Log.i("propose", propose+"");
                        Log.i("planning", planning+"");
                        Log.i("tool", tool+"");
                        Log.i("advice", advice+"");
                        Log.i("improve", improve+"");
                        Log.i("quality_report", quality_report+"");
                        Log.i("quality_project", quality_project+"");
                        Log.i("getMessage", t.getMessage()+"");
                    }
                });
            }
        }
    }

    public void back()
    {
        Intent Menu = new Intent(this,UserScheduleActivity.class);
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
        ss7 = s7+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss7.equals(myAdapter2.getItem(i)))
            {
                grade6.setSelection(i);
                break;
            }
        }
    }

}
