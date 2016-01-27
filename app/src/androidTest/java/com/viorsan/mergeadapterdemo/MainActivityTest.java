package com.viorsan.mergeadapterdemo;

import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 27.01.16.
 */

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Есть контрол с  'Today' строкой
     */
    @Test
    public void shouldHaveToday() {
        // then
        onView(withText("Today")).check(matches(isDisplayed()));
    }

    /**
     * Есть контрол с 'Before' - локализуемым ресурсом
     */
    @Test
    public void shouldHaveBefore() {
        // then
        onView(withId(R.string.before)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldHaveYesterday() {
        // then
        onView(withId(R.string.yesterday)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldHaveTwoDaysAgo() {
        // then
        onView(withId(R.string.twodaysago)).check(matches(isDisplayed()));
    }
    @Test
    public void shouldHaveThreeDaysAgo() {
        // then
        onView(withId(R.string.threedaysago)).check(matches(isDisplayed()));
    }



}
