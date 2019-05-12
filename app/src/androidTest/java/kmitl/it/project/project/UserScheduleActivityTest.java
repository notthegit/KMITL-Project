package kmitl.it.project.project;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class UserScheduleActivityTest {

    @Rule
    public ActivityTestRule<UserScheduleActivity> mActivityTestRule = new ActivityTestRule<UserScheduleActivity>(UserScheduleActivity.class);
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ProjectDetail.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onCreateOptionsMenu() {
    }

    @Test
    public void onOptionsItemSelected() {
    }

    @Test
    public void clickProject() {

        getInstrumentation().waitForMonitorWithTimeout(monitor, 1000);
    }
}