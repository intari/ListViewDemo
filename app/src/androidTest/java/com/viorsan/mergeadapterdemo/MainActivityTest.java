package com.viorsan.mergeadapterdemo;

import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 27.01.16.
 */


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Есть контрол с  'Today' - локализуемым ресурсом
     * Не работает если контрол не на экране (а в случае списка так может быть запросто)
     */
    @Test
    public void shouldHaveToday() {
        // then
        onView(withText(R.string.today)).check(matches(isDisplayed()));

    }

    /**
     * Есть контрол с 'Before' - локализуемым ресурсом
     */
    @Test
    public void shouldHaveBefore() {
        // then
        onData(allOf(withText(R.string.before))).check(matches(isDisplayed()));
    }
    @Test
    public void shouldHaveYesterday() {
        // then
        onData(allOf(withText(R.string.yesterday))).check(matches(isDisplayed()));
    }
    @Test
    public void shouldHaveTwoDaysAgo() {
        // then
        onData(allOf(withText(R.string.twodaysago))).check(matches(isDisplayed()));
    }
    @Test
    public void shouldHaveThreeDaysAgo() {
        // then
        onData(allOf(withText(R.string.threedaysago))).check(matches(isDisplayed()));
    }


}
