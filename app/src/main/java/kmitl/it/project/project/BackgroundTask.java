//package kmitl.it.project.project;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
////public class BackgroundTask extends AsyncTask<String, Void, String> {
////    AlertDialog alertDialog;
////    Context context;
////    BackgroundTask(Context ctx)
////    {
////        context = ctx;
////    }
////
////    @Override
////    protected void onPreExecute() {
////        alertDialog = new AlertDialog.Builder(context).create();
////        alertDialog.setTitle("Login Status");
////    }
////
////    @Override
////    protected String doInBackground(String... params) {
////        String loginUrl = "http://10.0.2.2/login.php";
////        String method = params[0];
////        if (method.equals("login"))
////        {
////            String loginUsername = params[1], loginPassword = params[2];
////            try {
////                URL url = new URL(loginUrl);
////                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
////                httpURLConnection.setRequestMethod("POST");
////                httpURLConnection.setDoOutput(true);
////                httpURLConnection.setDoInput(true);
////                OutputStream outputStream = httpURLConnection.getOutputStream();
////                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
////                        (outputStream, "UTF-8"));
////                String data = URLEncoder.encode("loginUsername", "UTF-8") + "=" +
////                        URLEncoder.encode(loginUsername, "UTF-8") + "&" +
////                        URLEncoder.encode("loginPassword", "UTF-8") + "=" +
////                        URLEncoder.encode(loginPassword, "UTF-8");
////                bufferedWriter.write(data);
////                bufferedWriter.flush();
////                bufferedWriter.close();
////                outputStream.close();
////
////                InputStream inputStream = httpURLConnection.getInputStream();
////                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
////                        (inputStream, "iso-8859-1"));
////                String response = "";
////                String line = "";
////                while ((line = bufferedReader.readLine()) != null)
////                {
////                    response += line;
////                }
////                bufferedReader.close();
////                inputStream.close();
////                httpURLConnection.disconnect();
////                return response;
////
////
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////
////        return null;
////    }
////
////    @Override
////    protected void onProgressUpdate(Void... values) {
////        super.onProgressUpdate(values);
////    }
////
////    @Override
////    protected void onPostExecute(String result) {
//////        if(result.equals("Login Information"))
//////        {
////            alertDialog.setMessage(result);
////            alertDialog.show();
//////        }
////    }
////
////
////}
//
//public class BackgroundTask extends AsyncTask<Void, Void, Void> {
//
//    String data = "";
//    String dataParsed = "";
//    String singleParsed = "";
//
//    String schDate1;
//    String schTime1;
//    String schRoom1;
//    String schName1;
//    String totalProject;
//    List<String> schDate = new ArrayList<String>();
//    List<String> schTime = new ArrayList<String>();
//    List<String> schRoom = new ArrayList<String>();
//    List<String> schName = new ArrayList<String>();
//    String aa;
//    String bb;
//    String cc;
//    String dd;
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        try {
////            URL url = new URL("http://127.0.0.1:8000/api/schedule/?format=json&login_user_id=36");
////            URL url = new URL("http://127.0.0.1:8000/api/schedule/?login_user_id=36");
//            URL url = new URL("https://api.myjson.com/bins/18vbtc");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while (line != null){
//                line = bufferedReader.readLine();
//                data += line;
//            }
//            JSONArray JA = new JSONArray(data);
//            totalProject = "" + JA.length();
//            for (int i = 0;i < JA.length();i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed = JO.get("date_exam") + " " + JO.get("time") + " " +
//                        JO.get("rooom_name") + " " + JO.get("proj_name_en") + "\n";
//                dataParsed += singleParsed;
//
//                schDate1 = JO.get("date_exam")+"";
//                schTime1 = JO.get("time")+"";
//                schRoom1 = JO.get("rooom_name")+"";
//                schName1 = JO.get("proj_name_en")+"";
//                schDate.add(schDate1);
//                schTime.add(schTime1);
//                schRoom.add(schRoom1);
//                schName.add(schName1);
//            }
//            aa = schDate.toString();
//            bb = schTime.toString();
//            cc = schRoom.toString();
//            dd = schName.toString();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        UserScheduleActivity.data.setText(this.dataParsed);
//        UserScheduleActivity.schDate1.setText(this.aa);
//        UserScheduleActivity.schTime1.setText(this.bb);
//        UserScheduleActivity.schRoom1.setText(this.cc);
//        UserScheduleActivity.schName1.setText(this.dd);
//        UserScheduleActivity.www.setText(this.totalProject);
//    }
//}