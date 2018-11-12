package kmitl.it.project.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Spinner name = (Spinner) findViewById(R.id.nameSpinner);
        Spinner grade = (Spinner) findViewById(R.id.textView2Spinner);
        Spinner grade1 = (Spinner) findViewById(R.id.textView3Spinner);
        Spinner grade2 = (Spinner) findViewById(R.id.textView4Spinner);
        Spinner grade3 = (Spinner) findViewById(R.id.textView5Spinner);
        Spinner grade4 = (Spinner) findViewById(R.id.textView6Spinner);
        Spinner grade5 = (Spinner) findViewById(R.id.textView7Spinner);
        Spinner grade6 = (Spinner) findViewById(R.id.textView8Spinner);
        Spinner grade7 = (Spinner) findViewById(R.id.textView9Spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GradeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray
                (R.array.projectName));
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(GradeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray
                (R.array.grade));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(myAdapter);
        grade.setAdapter(myAdapter2);
        grade1.setAdapter(myAdapter2);
        grade2.setAdapter(myAdapter2);
        grade3.setAdapter(myAdapter2);
        grade4.setAdapter(myAdapter2);
        grade5.setAdapter(myAdapter2);
        grade6.setAdapter(myAdapter2);
        grade7.setAdapter(myAdapter2);

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
