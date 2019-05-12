package kmitl.it.project.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import androidx.test.espresso.intent.Intents;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String username = "it58070046";
    private String password = "Lot253929054";
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(UserScheduleActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void pass(){
        Espresso.onView(withId(R.id.username)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.loginBtn)).perform(click());
        getInstrumentation().waitForMonitorWithTimeout(monitor, 7000);
    }

    @After
    public void tearDown() throws Exception {
    }
}