package io.git.movies.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.git.movies.bakingapp.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnFirstElementAndCheckToolbarTitle() throws InterruptedException {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Nutella Pie")));
    }

    @Test
    public void selectTheFirstRecipeAndCheckStepsFragment() throws InterruptedException {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.steps_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.steps_recycler_view)).check(matches(hasDescendant(withText("Step 1: Starting prep"))));
        onView(withId(R.id.videoFragmentPlaceholder)).check(matches(not(isDisplayed())));
    }

    @Test
    public void selectTheFirstRecipeAndClickOnFirstStepItem() throws InterruptedException {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.steps_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.steps_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.videoFragmentPlaceholder)).check(matches(isDisplayed()));
        onView(withId(R.id.stepDescriptionTv)).check(matches(withText("Recipe Introduction")));
    }
}
