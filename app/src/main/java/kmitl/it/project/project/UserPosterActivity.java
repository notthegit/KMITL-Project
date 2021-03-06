package kmitl.it.project.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPosterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem2> listItems;
    private boolean dbOff = true;
//    private String URL_DATA = "https://api.myjson.com/bins/ck9lk";
//    private String URL_DATA = "http://10.0.2.2:8000/api/scheduleposter/?format=json&login_user_id=8";
    private String URL_DATA = "http://10.0.2.2:8000/api/scheduleposter/?format=json&login_user_id=";
    public static String id_user = "";
    public static String login_user = "";
    public static String name = "";
    public static boolean staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule_api2);

        if (getIntent().hasExtra("id")) {
            id_user = getIntent().getStringExtra("id");
            URL_DATA += id_user;
        }
        if (getIntent().hasExtra("login_user")) {
            login_user = getIntent().getStringExtra("login_user");
        }
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        }
        if (getIntent().hasExtra("staff")) {
            staff = getIntent().getExtras().getBoolean("staff");
        }

        recyclerView = (RecyclerView)findViewById(R.id.schPosterRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecycleViewData();

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
    }


    private void loadRecycleViewData(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Schedule...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("sch_poster");
                            for (int i = 0; i<array.length();i++){
                                JSONObject o = array.getJSONObject(i);
                                ListItem2 item = new ListItem2("Date : "+(o.getString("date_post"))
                                        ,("Project : "+o.getString("proj_name_en"))
                                        ,(""+o.getString("id"))
                                        ,id_user
                                        ,login_user
                                        ,name
                                        ,staff);
                                listItems.add(item);
                            };
                            adapter = new SchPosterAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        progressDialog.hide();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
            if(id == R.id.setting)
            {
                Intent Menu = new Intent(this,SettingActivity.class);
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
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,SystemOfflineActivity.class);
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
        }
        else if (staff)
        {
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,UserScheduleActivity.class);
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
            if(id == R.id.logout)
            {
                Intent Menu = new Intent(this,MainActivity.class);
                startActivity(Menu);
            }
        }
        else
        {
            if(id == R.id.result)
            {
                Intent Menu = new Intent(this,UserProjectResultActivity.class);
                Menu.putExtra("id", id_user);
                Menu.putExtra("name", name);
                Menu.putExtra("staff", staff);
                Menu.putExtra("login_user", login_user);
                startActivity(Menu);
            }
            if(id == R.id.schedule)
            {
                Intent Menu = new Intent(this,UserScheduleActivity.class);
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
