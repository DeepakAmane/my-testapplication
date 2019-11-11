package com.example.testapp;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.testapp.adapter.StatementListAdapter;
import com.example.testapp.ui.LoginActivity;
import com.example.testapp.ui.StatementListActivity;

import android.view.View;
import android.widget.ArrayAdapter;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.instanceOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class StatementListActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Rule
    public ActivityTestRule<StatementListActivity> activityTestRule = new ActivityTestRule<StatementListActivity>(StatementListActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, StatementListActivity.class);
            result.putExtra("MyClass", InjectUserAccountDetails.injectUserAccount());
            return result;
        }
    };

    @Before
    public void setUp() throws Exception {
        // before test case execution
    }

    @Test
    public void ensureRecyclerViewIsPresentWithStatementList() throws Exception {
        Thread.sleep(5000);
        StatementListActivity activity = activityTestRule.getActivity();
        View viewById = activity.findViewById(R.id.statementListRecylerview);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) viewById;
        StatementListAdapter adapter = (StatementListAdapter) recyclerView.getAdapter();
        assertThat(adapter, instanceOf(StatementListAdapter.class));
        assert adapter != null;
        assertThat(adapter.getItemCount(), greaterThan(0));
    }
}
