package kmitl.it.project.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class UserScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();

        if(id == R.id.grade)
        {
            Intent Menu = new Intent(this,GradeActivity.class);
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

    public void clickProject(View view){
        Intent pop = new Intent(this,DetailProjectPop.class);
        startActivity(pop);
    }
}
