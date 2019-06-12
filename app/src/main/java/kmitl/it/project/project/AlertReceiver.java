package kmitl.it.project.project;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static kmitl.it.project.project.App.CHANNEL_1_ID;

public class AlertReceiver extends BroadcastReceiver {

    String allDatePost, allDateExam, roomName;
    String note;
    private NotificationManagerCompat notificationManager;
    String[] schPoster, sch, schSplit;
    private boolean dateMatch = false;
    Context context1;
    Intent intent1;

    @Override
    public void onReceive(Context context, Intent intent) {
        context1 = context;
        intent1 = intent;
        Log.i("Notify", "time");
        note = "asss";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context, UserScheduleActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "0")
//                .setContentIntent(pendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("ใกล้ถึงเวลาสอบโครงงาน")
                .setContentText(note)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setAutoCancel(true);
        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());








        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ScheduleApi> call = api.getSchedule(33);
        Log.i("alarm", "alarm");
        call.enqueue(new Callback<ScheduleApi>() {
            @Override
            public void onResponse(Call<ScheduleApi> call, Response<ScheduleApi> response) {
                ScheduleApi scheduleApi = response.body();
                if (response.isSuccessful()) {
                    schPoster = scheduleApi.getSch_poster().split(",");
                    sch = scheduleApi.getSch().split(",");
                    for (String i : schPoster) {
                        Log.i("alarm_list", i);
                    }
                    for (String i : sch) {
                        Log.i("alarm_list2", i);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            alarm(context1, intent1);
                        }
                    }, 3000);   //3 seconds
                    return;
                }
                Log.i("alarm", "!response.isSuccessful()   ");
            }

            @Override
            public void onFailure(Call<ScheduleApi> call, Throwable t) {
                Log.i("alarm", "onFailure");
            }
        });

//        note = "ssss";
////        if (dateMatch)
////        {
//            Log.i("dateMatch", dateMatch+"");
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//            Intent repeating_intent = new Intent(context, UserScheduleActivity.class);
//            repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
////        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "100")
//                    .setContentIntent(pendingIntent)
//                    .setDefaults(NotificationCompat.DEFAULT_ALL)
//                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
//                    .setContentTitle("ใกล้ถึงเวลาสอบโครงงาน")
//                    .setContentText(note)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .setCategory(NotificationCompat.CATEGORY_ALARM)
//                    .setAutoCancel(true);
////        notificationManager.notify(100, builder.build());
//            Log.i("Notify", "if");
//            if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
//                notificationManager.notify(100, builder.build());
//                Log.i("Notify", "Alarm");
//            }
////        }
//        Log.i("dateMatch_pass_if", dateMatch+"");
    }

    public void alarm(Context context, Intent intent) {

        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
//        String date = "14/12/2017";
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
        Log.i("dateMatch", dateMatch+" ");
        if (dateMatch)
        {
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            Intent repeating_intent = new Intent(context, UserScheduleActivity.class);
            repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "100")
                    .setContentIntent(pendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle("ใกล้ถึงเวลาสอบโครงงาน")
                    .setContentText(note)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

            notificationManager.notify(100, builder.build());
            Log.i("Notify", "if");
            if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
                notificationManager.notify(100, builder.build());
                Log.i("Notify", "Alarm");
            }

        }

        Toast.makeText(context, note, Toast.LENGTH_LONG).show();
        Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(10000);
    }
}
