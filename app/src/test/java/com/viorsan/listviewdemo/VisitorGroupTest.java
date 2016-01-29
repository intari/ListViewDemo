package com.viorsan.listviewdemo;

import com.viorsan.listviewdemo.Models.DATE_TYPE;
import com.viorsan.listviewdemo.Models.Visitor;
import com.viorsan.listviewdemo.Models.VisitorGroup;
import com.viorsan.listviewdemo.Models.Visitors;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 29.01.16.
 */
public class VisitorGroupTest {

    @Before
    public void setup() {
        Visitors.get().sort();

    }
    @Test
    public void testVisitorGroupsAreOk() {
        VisitorGroup todayGroup;
        todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        assertTrue("Today's visitorGroup type is wrong",DATE_TYPE.TODAY.equals(todayGroup.getDateType()));

        for (Visitor v:todayGroup.getVisitors()) {
            assertTrue("One of objects in today's visitorGroup has wrong type",DATE_TYPE.TODAY.equals(v.getDateType()));
            assertNotNull("One of objects in visitorGroup has no name",v.getName());
            assertNotNull("One of objects in visitorGroup has no date",v.getDate());


        }

    }
}
