package com.example.testapp;

import android.util.Log;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.testapp.ui.LoginActivity;
import com.example.testapp.ui.StatementListActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() throws Exception {
        // before test case execution
    }

    @Test
    public void loginWithoutUserShouldDisplayError() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.username_editText)).perform(typeText(""));
        onView(withId(R.id.password_editText)).perform(typeText("John123@#"), closeSoftKeyboard());
        Thread.sleep(5000);
        onView(withId(R.id.login_button)).perform(click());
        LoginActivity activity = activityTestRule.getActivity();
        Assert.assertEquals(activity.getResources().getString(R.string.enter_email), activity.getmViewModel().getToastMessage().getValue());
    }

    @Test
    public void loginWithoutPasswordShouldDisplayError() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.username_editText)).perform(typeText("john@gmail.com"));
        onView(withId(R.id.password_editText)).perform(typeText(""), closeSoftKeyboard());
        Thread.sleep(5000);
        onView(withId(R.id.login_button)).perform(click());
        LoginActivity activity = activityTestRule.getActivity();
        Assert.assertEquals(activity.getResources().getString(R.string.enter_password), activity.getmViewModel().getToastMessage().getValue());
    }

    @Test
    public void successfulLoginShouldOpenStatementListScreen() throws InterruptedException {
        onView(withId(R.id.username_editText)).perform(typeText("john@gmail.com"));
        onView(withId(R.id.password_editText)).perform(typeText("John123@#"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        Thread.sleep(5000);
        LoginActivity activity = activityTestRule.getActivity();
        assertNotNull(activity.getmViewModel().getLoginUser().getValue());

        Thread.sleep(5000);
    }
}
