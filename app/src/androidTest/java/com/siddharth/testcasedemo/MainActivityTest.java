package com.siddharth.testcasedemo;

import android.app.Instrumentation;
import android.support.test.rule.*;
import android.view.View;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity;

    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SecondActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void testView() {
        View view = mainActivity.findViewById(R.id.tvHello);
        assertNotNull(view);
        assertNotNull(mainActivity.findViewById(R.id.btnStart));
        onView(withId(R.id.btnStart)).perform(click());
        getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

    }

    @After
    public void tearDown() throws Exception {
    }
}