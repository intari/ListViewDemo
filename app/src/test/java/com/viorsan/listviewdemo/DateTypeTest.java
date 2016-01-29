package com.viorsan.listviewdemo;


import android.os.Build;

import com.viorsan.listviewdemo.Models.DATE_TYPE;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 29.01.16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class DateTypeTest {

    @Test
    public void testDateTypeCorrectnessToday() {
        DATE_TYPE dateType=DATE_TYPE.TODAY;
        String value= RuntimeEnvironment.application.getString(R.string.today);
        assertTrue("Today:date type's text is conversion is wrong",value.equals(dateType.getStringValue(RuntimeEnvironment.application)));
    }
    @Test
    public void testDateTypeCorrectnessYesterday() {
        DATE_TYPE dateType=DATE_TYPE.YESTERDAY;
        String value= RuntimeEnvironment.application.getString(R.string.yesterday);
        assertTrue("Yesterday:date type's text is conversion is wrong", value.equals(dateType.getStringValue(RuntimeEnvironment.application)));
    }
    @Test
    public void testDateTypeCorrectnessTwoDaysAgo() {
        DATE_TYPE dateType=DATE_TYPE.TWO_DAYS_AGO;
        String value= RuntimeEnvironment.application.getString(R.string.twodaysago);
        assertTrue("2 days ago:Date type's text is conversion is wrong",value.equals(dateType.getStringValue(RuntimeEnvironment.application)));
    }
    @Test
    public void testDateTypeCorrectnessThreeDaysAgo() {
        DATE_TYPE dateType=DATE_TYPE.THREE_DAYS_AGO;
        String value= RuntimeEnvironment.application.getString(R.string.threedaysago);
        assertTrue("3 days ago:Date type's text is conversion is wrong",value.equals(dateType.getStringValue(RuntimeEnvironment.application)));
    }
    @Test
    public void testDateTypeCorrectnessBefore() {
        DATE_TYPE dateType=DATE_TYPE.BEFORE;
        String value= RuntimeEnvironment.application.getString(R.string.before);
        assertTrue("Before:Date type's text is conversion is wrong",value.equals(dateType.getStringValue(RuntimeEnvironment.application)));
    }



}
