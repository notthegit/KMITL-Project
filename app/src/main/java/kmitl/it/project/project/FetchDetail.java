package kmitl.it.project.project;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDetail extends AsyncTask<Void, Void, Void> {

    String data = "", teach2NameCheck = "";
    String nameENName, nameTHName, major, stu1Name, stu2Name, year, term, teach1Name, teach2Name;
    String stu1, stu2;
    String proId;

    @Override
    protected Void doInBackground(Void... voids) {

        try {
//            URL url = new URL("https://api.myjson.com/bins/179hhq");
//            proId = "2260";
            proId = ProjectDetail.getProject_id();
            URL url = new URL("http://10.0.2.2:8000/api/project/?format=json&proj_id_id="+proId);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data += line;
            }
            JSONObject JOMain = new JSONObject(data);
            JSONObject JO = JOMain.getJSONObject("pro_detail");
            nameTHName = JO.get("proj_name_th") + "";
            nameENName = JO.get("proj_name_en") + "";
            year = "ปีการศึกษา: " + JO.get("proj_years");
            term = "เทอม: " + JO.get("proj_semester") + "";
            teach1Name = JO.get("proj_advisor") + "";
            teach2NameCheck = JO.get("proj_co_advisor") + "";
            if (teach2NameCheck == "")
            {
                teach2NameCheck = "ไม่มี";
            }
            teach2Name =  teach2NameCheck;
            major = "แขนง: " + JO.get("proj_major");
            JSONArray jsonArray = new JSONArray(JO.get("student_id") + "");
            JSONArray jsonArray2 = new JSONArray(JO.get("student_name") + "");
            stu1 = jsonArray.getString(0) + " " + jsonArray2.getString(0);
            stu2 = jsonArray.getString(1) + " " + jsonArray2.getString(1);
            if (stu2 == "")
            {
                stu2 = "ไม่มี";
            }
            stu1Name = stu1;
            stu2Name = stu2;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ProjectDetail.nameTHName.setText(this.nameTHName);
        ProjectDetail.nameENName.setText(this.nameENName);
        ProjectDetail.major.setText(this.major);
        ProjectDetail.stu1Name.setText(this.stu1Name);
        ProjectDetail.stu2Name.setText(this.stu2Name);
        ProjectDetail.year.setText(this.year);
        ProjectDetail.term.setText(this.term);
        ProjectDetail.teach1Name.setText(this.teach1Name);
        ProjectDetail.teach2Name.setText(this.teach2Name);
    }
}
