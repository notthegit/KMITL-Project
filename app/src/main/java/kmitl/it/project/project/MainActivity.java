package kmitl.it.project.project;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import kmitl.it.project.project.Model.apitest;
import kmitl.it.project.project.Remote.testService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static kmitl.it.project.project.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {
//    public static TextView data;

    EditText usernameET, passwordET;
    TextView test, test2;
    testService testapi;
    String note;
    String result = "";
    String[] schPoster, sch, schSplit;
    private boolean dbOff = true;
    private boolean loginFail = true;
    private boolean dateMatch = false;
    DatabaseHelper mDatabaseHelper;
    String name;
    String usernames;
    String id_user = "0";
    String login_user;
    boolean superuser;
    boolean staff;
//    data
//    public static TextView apiText1 = findViewById(R.id.apitest1);;
//    TextView textView1 = findViewById(R.id.titleUsername);
    private NotificationManagerCompat notificationManager;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.Title);
        tv.setText(stringFromJNI());
        notificationManager = NotificationManagerCompat.from(this);
        usernameET = (EditText)findViewById(R.id.username);
        passwordET = (EditText)findViewById(R.id.password);
        test = (TextView)findViewById(R.id.titleUsername);
        test2 = (TextView)findViewById(R.id.titlePassword);
        testapi = Common.gettestService();
        mDatabaseHelper = new DatabaseHelper(this);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 6);
        calendar.set(Calendar.SECOND, 00);
//        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        Intent intent = new Intent(this, AlertReceiver.class);

        intent.setAction("MY_NOTIFICATION_MESSAGE");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                PendingIntent.getBroadcast(this,1, intent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public void clickExit(View view){

        finish();
        System.exit(0);
    }

    public void clickLogin(View view){

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/")
//            .baseUrl("http://127.0.0.1:8000/api/")
//            .baseUrl("http://10.110.197.11:8000/api/")
//            .baseUrl("http://10.110.197.11/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        Api api = retrofit.create(Api.class);
        Call<List<SettingApi>> call = api.getSetting();
        call.enqueue(new Callback<List<SettingApi>>() {
        @Override
        public void onResponse(Call<List<SettingApi>> call, Response<List<SettingApi>> response) {
            if (!response.isSuccessful())
            {
//                    test.setText(response.code()); //id 404
                Log.d("if","!response.isSuccessful()");
                Log.d("code",response.code()+"");
                return;
            }
            List<SettingApi> setting = response.body();
            for (SettingApi setting1 : setting)
            {
//                test.setText(setting1.getActivate()+"");
                Log.d("if","pass for");
                if (setting1.getActivate() == 1)
                {
                    dbOff = false;
                    logins();
                }
                return;
            }
        }
        @Override
        public void onFailure(Call<List<SettingApi>> call, Throwable t) {
//                test.setText(t.getMessage());
            Log.d("ERROR",t.getMessage());
        }
    });

    Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
        public void run() {
            // yourMethod();
            nextActivity();
        }
    }, 6000);   //3 seconds

}

    public void nextActivity() {
        // yourMethod();
        if (loginFail)
        {
            Log.e("login","login fail");
            Toast.makeText(MainActivity.this, "Login Fail: "+result, Toast.LENGTH_LONG).show();
        }
        else if (dbOff)
        {
            Intent login1 = new Intent(this,SystemOfflineActivity.class);
            login1.putExtra("id", id_user);
            login1.putExtra("name", name);
            login1.putExtra("staff", staff);
            login1.putExtra("login_user", login_user);
            startActivity(login1);
        }
        else
        {
            Intent login1 = new Intent(this,UserScheduleActivity.class);
            login1.putExtra("id", id_user);
            login1.putExtra("name", name);
            login1.putExtra("staff", staff);
            login1.putExtra("login_user", login_user);
            startActivity(login1);
        }
    }

    private void getApi() {
        testapi.getApi1().enqueue(new Callback<apitest>() {
            TextView textView1 = findViewById(R.id.titleUsername);
            @Override
            public void onResponse(Call<apitest> call, Response<apitest> response) {
                textView1.setText(response.body().getApi1());
            }

            @Override
            public void onFailure(Call<apitest> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
                textView1.setText("Fail");
            }
        });
    }

    public void clickAlarm(View view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
//                .baseUrl("http://127.0.0.1:8000/api/")
//                .baseUrl("http://10.110.197.11/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ScheduleApi> call = api.getSchedule(33);
        Log.i("alarm", "alarm");
        call.enqueue(new Callback<ScheduleApi>() {
            @Override
            public void onResponse(Call<ScheduleApi> call, Response<ScheduleApi> response) {
                ScheduleApi scheduleApi = response.body();
                if (response.isSuccessful())
                {
                    schPoster = scheduleApi.getSch_poster().split(",");
                    sch = scheduleApi.getSch().split(",");
                    for (String i : schPoster)
                    {
                        Log.i("alarm_list", i);
                    }
                    for (String i : sch)
                    {
                        Log.i("alarm_list2", i);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        alarm();
                    }
                    }, 3000);   //3 seconds
                    return;
                }
                test.setText(response.code()); //id 404
                Log.i("alarm", "!response.isSuccessful()   ");
            }
            @Override
            public void onFailure(Call<ScheduleApi> call, Throwable t) {
                test.setText(t.getMessage());
                Log.i("onFailure", t.getMessage());
            }
        });

//        Intent alarm = new Intent(this,UserScheduleActivity.class);
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1,
//                alarm, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
//                .setContentTitle("ใกล้ถึงเวลาสอบโครงงาน")
////                .setContentText("Project 1 9.30")
//                .setContentText(allDate)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_ALARM)
//                .setContentIntent(resultPendingIntent)
//                .setAutoCancel(true)
//                .build();
//
//        notificationManager.notify(1, notification);

    }

    public void logins() {

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        Call<LoginApi> call = RetrofitClient
                .getInstance()
                .getApi()
                .login(username, password);
        Log.i("Notify", "LoginApi");
        call.enqueue(new Callback<LoginApi>() {
            @Override
            public void onResponse(Call<LoginApi> call, Response<LoginApi> response) {
                LoginApi s = response.body();
                result = response.body().getResult();
                if(result.equals("pass"))
                {
                    loginFail = false;
                    LoginApi loginApi = response.body();
                    name = loginApi.getTeacher_name();
                    usernames = loginApi.getUsername();
                    id_user = loginApi.getId();
                    login_user = loginApi.getLogin_user();
                    superuser = loginApi.isSuperuser();
                    staff = loginApi.isStaff();
                    Log.i("Notify", name);
                    Log.i("Notify", usernames);
                    Log.i("Notify", id_user+"");
                    Log.i("Notify", superuser+"");
                    Log.i("Notify", staff+"");
                    int superuserInt = 0;
                    int staffInt = 0;
                    if (superuser)
                    {
                        superuserInt = 1;
                    }
                    if (staff)
                    {
                        staffInt = 1;
                    }
                    AddData(id_user, usernames, name, superuserInt, staffInt);

                }
//                Toast.makeText(MainActivity.this, s.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Notify", "pass  _"+ loginFail);

            }

            @Override
            public void onFailure(Call<LoginApi> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Notify", "fail");
            }
        });

    }

    public void AddData(String id, String usernames, String name, int superuser, int staff) {
        boolean insertData = mDatabaseHelper.addData(id, usernames, name, superuser, staff);
//        mDatabaseHelper.addData(id, username, name, superuser, staff);

        if (insertData)
        {
            Log.i("AddData", "ok");
        }
        else
        {
            Log.i("AddData", "fail");
        }
    }

    public void alarm() {
        Intent alarm = new Intent(this,UserScheduleActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1,
                alarm, PendingIntent.FLAG_UPDATE_CURRENT);

       String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
//        String date = "21/05/2018";
//        String date = "23/05/2018";
        String[] parts2 = date.split("/");
        int day = Integer.parseInt(parts2[0]);
        int day2 = 2 + day;
        for (String i : schPoster)
        {
            if (dateMatch)
            {
                Log.i("break", "break");
                break;
            }
            Log.i("alarm_list", i);
            String[] parts = i.split("/");
            int month = Integer.parseInt(parts[1]);
            String monthS = month+"";
            if (month < 10)
            {
                monthS = "0"+monthS;
            }
            String dayS = day+"";
            String dayS2 = day2+"";
            if (day < 10)
            {
                dayS = "0"+dayS;
                dayS2 = "0"+dayS2;
            }
            if((dayS.equals(parts[0])) && (monthS.equals(parts2[1])) && (parts[2].equals(parts2[2])))
            {
                note = i + "    วันปัจจุบัน: " + date + "  มีคุมสอบวันนี้";
                dateMatch = true;
            }
            else if ((dayS2.equals(parts[0])) && (monthS.equals(parts2[1])) && (parts[2].equals(parts2[2])))
            {
                note = i + "    วันปัจจุบัน: " + date + "  มีคุมสอบอีก 2 วัน";
                dateMatch = true;
            }
            else {
                Log.i("alarm", i +"      "+ "    วันปัจจุบัน: " + date + "        " +dayS+parts[0]+parts2[0]+" "+monthS+parts[1]+parts2[1]+" "+parts[2]+parts2[2]);
            }
        }
        for (String i : sch)
        {
            if (dateMatch)
            {
                Log.i("break", "break");
                break;
            }
            schSplit = i.split("=");
            Log.i("alarm_list2", i);
            String[] parts3 = schSplit[2].split("/");
            int month2 = Integer.parseInt(parts3[1]);
            String monthS2 = month2+"";
            if (month2 < 10)
            {
                monthS2 = "0"+monthS2;
            }
            String dayS = day+"";
            String dayS2 = day2+"";
            if (day < 10)
            {
                dayS = "0"+dayS;
                dayS2 = "0"+dayS2;
            }
            if((dayS.equals(parts3[0])) && (monthS2.equals(parts2[1])) && (parts3[2].equals(parts2[2])))
            {
                note = schSplit[2] + "    วันปัจจุบัน: " + date + "  มีคุมสอบวันนี้    " + schSplit[1]+ "  " + schSplit[0];
                dateMatch = true;
            }
            else if ((dayS2.equals(parts3[0])) && (monthS2.equals(parts2[1])) && (parts3[2].equals(parts2[2])))
            {
                note = schSplit[2] + "    วันปัจจุบัน: " + date + "  มีคุมสอบอีก 2 วัน    " + schSplit[1]+ "  " + schSplit[0];
                dateMatch = true;
            }
            else {
                Log.i("alarm", schSplit[2]+ "    วันปัจจุบัน: " + date + "        " +dayS+parts3[0]+parts2[0]+" "+monthS2+parts3[1]+parts2[1]+" "+parts3[2]+parts2[2]);
            }
        }


        if (dateMatch)
        {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle("ใกล้ถึงเวลาสอบโครงงาน")
                    .setContentText(note)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true)
                    .build();

            notificationManager.notify(1, notification);
        }
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
