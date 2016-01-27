package com.viorsan.mergeadapterdemo;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.viorsan.mergeadapterdemo.Models.DATE_TYPE;

import org.junit.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void shouldDateTypeBefore() {
        // для этого теста контекст нужен хотя вроде бы обычный юнит текст так что...
        DATE_TYPE dt=DATE_TYPE.BEFORE;
        assertEquals(dt.getStringValue(getContext()), getContext().getString(R.string.before));
    }

    @Test
    public void shouldDateTypeToday() {
        // для этого теста контекст нужен хотя вроде бы обычный юнит текст так что...
        DATE_TYPE dt=DATE_TYPE.TODAY;
        assertEquals(dt.getStringValue(getContext()),getContext().getString(R.string.today));
    }

    @Test
    public void shouldDateTypeTwoDaysAgo() {
        // для этого теста контекст нужен хотя вроде бы обычный юнит текст так что...
        DATE_TYPE dt=DATE_TYPE.TWO_DAYS_AGO;
        assertEquals(dt.getStringValue(getContext()),getContext().getString(R.string.twodaysago));
    }
    @Test
    public void shouldDateTypeThreeDaysAgo() {
        // для этого теста контекст нужен хотя вроде бы обычный юнит текст так что...
        DATE_TYPE dt=DATE_TYPE.THREE_DAYS_AGO;
        assertEquals(dt.getStringValue(getContext()),getContext().getString(R.string.threedaysago));
    }

}