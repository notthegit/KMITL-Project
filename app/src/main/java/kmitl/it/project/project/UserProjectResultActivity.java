package kmitl.it.project.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserProjectResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_project_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();

        if(id == R.id.schedule)
        {
            Intent Menu = new Intent(this,UserScheduleActivity.class);
            startActivity(Menu);
        }
        if(id == R.id.grade2)
        {
            Intent Menu = new Intent(this,Grade2Activity.class);
            startActivity(Menu);
        }
        if(id == R.id.logout)
        {
            Intent Menu = new Intent(this,MainActivity.class);
            startActivity(Menu);
        }
        return super.onOptionsItemSelected(item);
    }
}
