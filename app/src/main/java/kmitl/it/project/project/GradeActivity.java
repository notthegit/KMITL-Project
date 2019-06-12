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

public class GradeActivity extends AppCompatActivity {

    public TextView g1, g2, g3, g4, g5, g6, g7, g8, g9, name;
    public int s1, s2, s3, s4, s5, s6, s7, s8, s9;
    public String ss1, ss2, ss3, ss4, ss5, ss6, ss7, ss8, ss9;
    int presentation, question, report, presentation_media, discover, analysis, quantity, levels;
    int quality;
    int grade_proj_id = 1;
    public static String project_id = "";
    public static String project_name = "";
    private boolean update = false;
    private boolean connect = true;
    private boolean dbOff = true;
    private boolean noPoster = true;
    public static String id_user = "";
    public static String names = "";
    public static String login_user = "";
    public static boolean staff;
    public Spinner grade, grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8;
    ArrayAdapter<String> myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        check();
        grade = (Spinner) findViewById(R.id.textView2Spinner);
        grade1 = (Spinner) findViewById(R.id.textView3Spinner);
        grade2 = (Spinner) findViewById(R.id.textView4Spinner);
        grade3 = (Spinner) findViewById(R.id.textView5Spinner);
        grade4 = (Spinner) findViewById(R.id.textView6Spinner);
        grade5 = (Spinner) findViewById(R.id.textView7Spinner);
        grade6 = (Spinner) findViewById(R.id.textView8Spinner);
        grade7 = (Spinner) findViewById(R.id.textView9Spinner);
        grade8 = (Spinner) findViewById(R.id.textView10Spinner);
        TextView projectName = (TextView) findViewById(R.id.name);
        g1 = (TextView) findViewById(R.id.g1);
        g2 = (TextView) findViewById(R.id.g2);
        g3 = (TextView) findViewById(R.id.g3);
        g4 = (TextView) findViewById(R.id.g4);
        g5 = (TextView) findViewById(R.id.g5);
        g6 = (TextView) findViewById(R.id.g6);
        g7 = (TextView) findViewById(R.id.g7);
        g8 = (TextView) findViewById(R.id.g8);
        g9 = (TextView) findViewById(R.id.g9);
        name = (TextView) findViewById(R.id.name);
        myAdapter2 = new ArrayAdapter<String>(GradeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray
                (R.array.grade));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        name.setAdapter(myAdapter);
        grade.setAdapter(myAdapter2);
        grade1.setAdapter(myAdapter2);
        grade2.setAdapter(myAdapter2);
        grade3.setAdapter(myAdapter2);
        grade4.setAdapter(myAdapter2);
        grade5.setAdapter(myAdapter2);
        grade6.setAdapter(myAdapter2);
        grade7.setAdapter(myAdapter2);
        grade8.setAdapter(myAdapter2);

        if (getIntent().hasExtra("id")) {
            id_user = getIntent().getStringExtra("id");
        }
        if (getIntent().hasExtra("login_user")) {
            login_user = getIntent().getStringExtra("login_user");
        }
        if (getIntent().hasExtra("project_id")) {
            project_id = getIntent().getStringExtra("project_id");//project_id
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
        Log.i("test", login_user);
//        Call<List<GradeApi>> call = api.getGrade(33, Integer.parseInt(project_id));
//        Call<List<GradeApi>> call = api.getGrade(Integer.parseInt(id_user), Integer.parseInt(project_id));
        Call<List<GradeApi>> call = api.getGrade(Integer.parseInt(login_user), Integer.parseInt(project_id));
        call.enqueue(new Callback<List<GradeApi>>() {
            @Override
            public void onResponse(Call<List<GradeApi>> call, Response<List<GradeApi>> response) {
                if (!response.isSuccessful())
                {
//                    test.setText(response.code()); //id 404
                    g1.setText(response.code()+"    " + project_id);
//                    g1.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
//                    g2.setText("ยังไม่มีการกรอกคะแนน");
//                    g3.setText("ยังไม่มีการกรอกคะแนน");
                    g2.setText("id_user "+id_user);
                    g3.setText("project_id "+project_id);
                    g4.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g5.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g6.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g7.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g8.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    g9.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                    connect = false;
                    return;
                }
                List<GradeApi> grade = response.body();
                for (GradeApi grades : grade)
                {
                    if (grades.isGrade())
                    {
                        g1.setText("คะแนนเก่า " + grades.getPresentation());
                        g2.setText("คะแนนเก่า " + grades.getQuestion());
                        g3.setText("คะแนนเก่า " + grades.getReport());
                        g4.setText("คะแนนเก่า " + grades.getPresentation_media());
                        g5.setText("คะแนนเก่า " + grades.getDiscover());
                        g6.setText("คะแนนเก่า " + grades.getAnalysis());
                        g7.setText("คะแนนเก่า " + grades.getQuantity());
                        g8.setText("คะแนนเก่า " + grades.getLevels());
                        g9.setText("คะแนนเก่า " + grades.getQuality());
                        grade_proj_id = grades.getId();
                        update = true;
                        s1 = grades.getPresentation();
                        s2 = grades.getQuestion();
                        s3 = grades.getReport();
                        s4 = grades.getPresentation_media();
                        s5 = grades.getDiscover();
                        s6 = grades.getAnalysis();
                        s7 = grades.getQuantity();
                        s8 = grades.getLevels();
                        s9 = grades.getQuality();
                        setSpinner();
                    }
                    else
                    {
                        g1.setText("ยังไม่มีการกรอกคะแนน");
                        g2.setText("ยังไม่มีการกรอกคะแนน");
                        g3.setText("ยังไม่มีการกรอกคะแนน");
                        g4.setText("ยังไม่มีการกรอกคะแนน");
                        g5.setText("ยังไม่มีการกรอกคะแนน");
                        g6.setText("ยังไม่มีการกรอกคะแนน");
                        g7.setText("ยังไม่มีการกรอกคะแนน");
                        g8.setText("ยังไม่มีการกรอกคะแนน");
                        g9.setText("ยังไม่มีการกรอกคะแนน");
                        grade_proj_id = Integer.parseInt(project_id);
                    }

                }
            }


            @Override
            public void onFailure(Call<List<GradeApi>> call, Throwable t) {
                g1.setText(t.getMessage()+ "");
//                g1.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
//                g2.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
//                g3.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g2.setText("id_user "+id_user);
                g3.setText("project_id "+project_id);
                g4.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g5.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g6.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g7.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g8.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                g9.setText("ไม่สามารถเชื่อมต่อกับฐานข้อมูล");
                connect = false;
            }
        });

        grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        presentation = 0;
                        break;

                    case 1:
                        presentation = 1;
                        break;

                    case 2:
                        presentation = 2;
                        break;

                    case 3:
                        presentation = 3;
                        break;

                    case 4:
                        presentation = 4;
                        break;

                    case 5:
                        presentation = 5;
                        break;

                    case 6:
                        presentation = 6;
                        break;

                    case 7:
                        presentation = 7;
                        break;

                    case 8:
                        presentation = 8;
                        break;

                    case 9:
                        presentation = 9;
                        break;

                    case 10:
                        presentation = 10;
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
                        question = 0;
                        break;

                    case 1:
                        question = 1;
                        break;

                    case 2:
                        question = 2;
                        break;

                    case 3:
                        question = 3;
                        break;

                    case 4:
                        question = 4;
                        break;

                    case 5:
                        question = 5;
                        break;

                    case 6:
                        question = 6;
                        break;

                    case 7:
                        question = 7;
                        break;

                    case 8:
                        question = 8;
                        break;

                    case 9:
                        question = 9;
                        break;

                    case 10:
                        question = 10;
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
                        report = 0;
                        break;

                    case 1:
                        report = 1;
                        break;

                    case 2:
                        report = 2;
                        break;

                    case 3:
                        report = 3;
                        break;

                    case 4:
                        report = 4;
                        break;

                    case 5:
                        report = 5;
                        break;

                    case 6:
                        report = 6;
                        break;

                    case 7:
                        report = 7;
                        break;

                    case 8:
                        report = 8;
                        break;

                    case 9:
                        report = 9;
                        break;

                    case 10:
                        report = 10;
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
                        presentation_media = 0;
                        break;

                    case 1:
                        presentation_media = 1;
                        break;

                    case 2:
                        presentation_media = 2;
                        break;

                    case 3:
                        presentation_media = 3;
                        break;

                    case 4:
                        presentation_media = 4;
                        break;

                    case 5:
                        presentation_media = 5;
                        break;

                    case 6:
                        presentation_media = 6;
                        break;

                    case 7:
                        presentation_media = 7;
                        break;

                    case 8:
                        presentation_media = 8;
                        break;

                    case 9:
                        presentation_media = 9;
                        break;

                    case 10:
                        presentation_media = 10;
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
                        discover = 0;
                        break;

                    case 1:
                        discover = 1;
                        break;

                    case 2:
                        discover = 2;
                        break;

                    case 3:
                        discover = 3;
                        break;

                    case 4:
                        discover = 4;
                        break;

                    case 5:
                        discover = 5;
                        break;

                    case 6:
                        discover = 6;
                        break;

                    case 7:
                        discover = 7;
                        break;

                    case 8:
                        discover = 8;
                        break;

                    case 9:
                        discover = 9;
                        break;

                    case 10:
                        discover = 10;
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
                        analysis = 0;
                        break;

                    case 1:
                        analysis = 1;
                        break;

                    case 2:
                        analysis = 2;
                        break;

                    case 3:
                        analysis = 3;
                        break;

                    case 4:
                        analysis = 4;
                        break;

                    case 5:
                        analysis = 5;
                        break;

                    case 6:
                        analysis = 6;
                        break;

                    case 7:
                        analysis = 7;
                        break;

                    case 8:
                        analysis = 8;
                        break;

                    case 9:
                        analysis = 9;
                        break;

                    case 10:
                        analysis = 10;
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
                        quantity = 0;
                        break;

                    case 1:
                        quantity = 1;
                        break;

                    case 2:
                        quantity = 2;
                        break;

                    case 3:
                        quantity = 3;
                        break;

                    case 4:
                        quantity = 4;
                        break;

                    case 5:
                        quantity = 5;
                        break;

                    case 6:
                        quantity = 6;
                        break;

                    case 7:
                        quantity = 7;
                        break;

                    case 8:
                        quantity = 8;
                        break;

                    case 9:
                        quantity = 9;
                        break;

                    case 10:
                        quantity = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        levels = 0;
                        break;

                    case 1:
                        levels = 1;
                        break;

                    case 2:
                        levels = 2;
                        break;

                    case 3:
                        levels = 3;
                        break;

                    case 4:
                        levels = 4;
                        break;

                    case 5:
                        levels = 5;
                        break;

                    case 6:
                        levels = 6;
                        break;

                    case 7:
                        levels = 7;
                        break;

                    case 8:
                        levels = 8;
                        break;

                    case 9:
                        levels = 9;
                        break;

                    case 10:
                        levels = 10;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grade8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        quality = 0;
                        break;

                    case 1:
                        quality = 1;
                        break;

                    case 2:
                        quality = 2;
                        break;

                    case 3:
                        quality = 3;
                        break;

                    case 4:
                        quality = 4;
                        break;

                    case 5:
                        quality = 5;
                        break;

                    case 6:
                        quality = 6;
                        break;

                    case 7:
                        quality = 7;
                        break;

                    case 8:
                        quality = 8;
                        break;

                    case 9:
                        quality = 9;
                        break;

                    case 10:
                        quality = 10;
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


//        int proj_id = Integer.parseInt(project_id);
//        if (update == false) {
//            grade_proj_id = Integer.parseInt(project_id);
//            Call<ResponseBody> call = RetrofitClient
//                    .getInstance()
//                    .getApi()
//                    .saveGrade(grade_proj_id, presentation, question, report, presentation_media,
//                            discover, analysis, quantity, levels, quality);
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    String s = "Done";
//    //                String s = response.body().toString();
//                    Toast.makeText(GradeActivity.this, s, Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(GradeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//        else {
//            grade_proj_id = 2310;
//            Call<ResponseBody> call = RetrofitClient
//                    .getInstance()
//                    .getApi()
//                    .saveGradePUT(grade_proj_id, presentation, question, report, presentation_media,
//                            discover, analysis, quantity, levels, quality);
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    String s = "Done";
////                String s = response.body().toString();
//                    Toast.makeText(GradeActivity.this, s, Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(GradeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//        }
    }

    public void sent()
    {
        if (dbOff)
        {
            Toast.makeText(GradeActivity.this, "ขออภัยตอนนี้ระบบได้ปิดการกรอกคะแนนแล้ว", Toast.LENGTH_LONG).show();
        }
        else if (!connect)
        {
            Toast.makeText(GradeActivity.this, "ไม่สามารถเชื่อมต่อกับฐานข้อมูลเก่าได้", Toast.LENGTH_LONG).show();
        }
        else
        {
//            int proj_id = Integer.parseInt(project_id);
            if (!update) {
//                grade_proj_id = Integer.parseInt(project_id);
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .saveGrade(grade_proj_id, presentation, question, report, presentation_media
                                , discover, analysis, quantity, levels, quality,
                                Integer.parseInt(id_user));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "Done";
                        //                String s = response.body().toString();
                        Toast.makeText(GradeActivity.this, s, Toast.LENGTH_LONG).show();
                        back();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(GradeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
//                grade_proj_id = 2310;
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .saveGradePUT(grade_proj_id, presentation, question, report, presentation_media,
                                discover, analysis, quantity, levels, quality);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "Done";
//                String s = response.body().toString();
                        Toast.makeText(GradeActivity.this, s, Toast.LENGTH_LONG).show();
                        back();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(GradeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
        ss8 = s8+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss8.equals(myAdapter2.getItem(i)))
            {
                grade7.setSelection(i);
                break;
            }
        }
        ss9 = s9+"";
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(ss9.equals(myAdapter2.getItem(i)))
            {
                grade8.setSelection(i);
                break;
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
}
