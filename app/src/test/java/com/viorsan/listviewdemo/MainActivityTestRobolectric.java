package com.viorsan.listviewdemo;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 29.01.16.
 */
// Static imports for assertion methods
import android.app.Activity;
import android.os.Build;
import android.widget.ListView;

import com.viorsan.listviewdemo.Adapters.VisitorListAdapter;
import com.viorsan.listviewdemo.Models.DATE_TYPE;
import com.viorsan.listviewdemo.Models.Visitor;
import com.viorsan.listviewdemo.Models.VisitorGroup;
import com.viorsan.listviewdemo.Models.Visitors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenu;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.ActivityController;


import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

//per https://github.com/codepath/android_guides/wiki/Unit-Testing-with-Robolectric Robolectric doesn't support even Android 5.1 so specify Android 5.0
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTestRobolectric {
    private MainActivity activity;
    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(MainActivity.class);

        ShadowLog.stream = System.out; //This is for printing log messages in console
    }
    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateListView() {
        ListView tvListView=(ListView) activity.findViewById(android.R.id.list);
        assertNotNull("ListView could not be found", tvListView);
    }
    @Test
    public void validateGetPeopleVisitedAtWorks() {
        Visitors.get().sort();
        VisitorGroup todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        assertNotNull("VisitorGroup Today is null",todayGroup);
    }
    @Test
    public void validateVisitorGroupTodayInListView() {
        Visitors.get().sort();
        VisitorGroup todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        ArrayList<VisitorGroup> groups=new ArrayList<>();
        groups.add(todayGroup);
        VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                activity,groups);
        activity.setListAdapter(visitorListAdapter);
        assertNotNull("ListView wasn't setup correctly", activity.getListView());
        assertNotNull("ListAdapter wasn't setup correctly", activity.getListAdapter());

    }
    @Test
    public void validateListViewContentsToday() {
        ListView listView=(ListView)activity.getListView();
        assertNotNull("ListView not found", listView);
        ShadowListView shadowListView=Shadows.shadowOf(listView);
        Visitors.get().sort();
        VisitorGroup todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        ArrayList<VisitorGroup> groups=new ArrayList<>();
        groups.add(todayGroup);
        VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                activity,groups);
        activity.setListAdapter(visitorListAdapter);
        shadowListView.populateItems();//прогружаем список
        String correctHeaderLine = RuntimeEnvironment.application.getString(R.string.today);

        //проверяем что первое поле это действительно 'Today'
        //да - у нас следующий тест отдельно проверяет ListAdapter но тут мы проверяем ListView
        //более правильно все эти
        ShadowLog.d("Checking that 'Today' exist", (String) listView.getAdapter().getItem(0));
        assertTrue("No 'today'", correctHeaderLine.equals((String) listView.getAdapter().getItem(0)));
        assertTrue("too much data in listview", listView.getAdapter().getCount() != Visitors.NUM_VISITS_TO_GENERATE);
    }
    @Test
    public void validateListViewContentsYesterday() {
        ListView listView=(ListView)activity.getListView();
        assertNotNull("ListView not found", listView);
        ShadowListView shadowListView=Shadows.shadowOf(listView);
        Visitors.get().sort();
        VisitorGroup todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        VisitorGroup yesterdayGroup=new VisitorGroup(
                DATE_TYPE.YESTERDAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.YESTERDAY)
        );
        ArrayList<VisitorGroup> groups=new ArrayList<>();
        groups.add(todayGroup);
        groups.add(yesterdayGroup);
        VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                activity,groups);
        activity.setListAdapter(visitorListAdapter);
        shadowListView.populateItems();//прогружаем список
        String correctHeaderLine = RuntimeEnvironment.application.getString(R.string.yesterday);

        //проверяем что первое поле это действительно 'Yesterday'
        //да - у нас следующий тест отдельно проверяет ListAdapter но тут мы проверяем ListView
        ShadowLog.d("Checking that 'Yesterday' exist", (String) listView.getAdapter().getItem(Visitors.NUM_VISITS_TO_GENERATE+1));
        assertTrue("No 'Yesterday'", correctHeaderLine.equals((String) listView.getAdapter().getItem(Visitors.NUM_VISITS_TO_GENERATE+1)));
    }

    @Test
    public void validateVisitorListAdapter() {
        Visitors.get().sort();
        VisitorGroup todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        ArrayList<VisitorGroup> groups=new ArrayList<>();
        groups.add(todayGroup);
        VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                activity,groups);

        //проверяем что ListAdapter коррктные данные данные для нашего случая возвращает
        assertTrue("VisitorListAdapter has zero elements", 0 != visitorListAdapter.getCount());
        assertTrue("VisitorListAdapter has incorrect number of types", 2 == visitorListAdapter.getViewTypeCount());
        assertTrue("VisitorListAdapter's header type is wrong", visitorListAdapter.getItem(0) instanceof String);
        assertTrue("VisitorListAdapter's line type is wrong", visitorListAdapter.getItem(1) instanceof Visitor);
        assertTrue("VisitorListAdapter's header is wrong", "Today".equals((String) visitorListAdapter.getItem(0)));
        assertTrue("VisitorListAdapter's number of elements is wrong",(Visitors.NUM_VISITS_TO_GENERATE+1)==visitorListAdapter.getCount());
        String correctHeaderLine = RuntimeEnvironment.application.getString(R.string.today);
        assertTrue("VisitorListAdapter's header line", correctHeaderLine.equals((String) visitorListAdapter.getItem(0)));
        assertTrue("VisitorListAdapter's data size is wrong", Visitors.NUM_VISITS_TO_GENERATE!=visitorListAdapter.getCount());

    }


    @Test(expected = IllegalArgumentException.class)
    public void validateVisitorListAdapterGetItemThrowExceptionIfInvalidData() {
        Visitors.get().sort();
        VisitorGroup todayGroup=new VisitorGroup(
                DATE_TYPE.TODAY,
                Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
        );
        ArrayList<VisitorGroup> groups=new ArrayList<>();
        groups.add(todayGroup);
        VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                activity,groups);

        //проверяем что ListAdapter коррктно дохнет
        visitorListAdapter.getItem(Integer.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateVisitorListAdapterGetItemThrowExceptionIfNoData() {
        Visitors.get().sort();
        ArrayList<VisitorGroup> groups=new ArrayList<>();
        VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                activity,groups);

        //проверяем что ListAdapter коррктно дохнет
        visitorListAdapter.getItem(0);
    }
    /*
    @Test
    public void validateOptionsMenu() {
        MainActivity act = Robolectric.setupActivity(MainActivity.class);
        ShadowActivity shadowActivity= Shadows.shadowOf(act);
        RoboMenu menu=new RoboMenu();
        shadowActivity.onCreateOptionsMenu(menu);
        assertNotNull("Options menu was not created", shadowActivity.getOptionsMenu());
    }
    @Test
    public void validateOptionsMenuInRegularLifecycle() {
        ActivityController activityController=Robolectric.buildActivity(MainActivity.class);
        Activity act=(Activity)activityController.create().start().resume().get();
        ShadowActivity shadowActivity= Shadows.shadowOf(act);
        assertNotNull("Options menu was not created in regular lifecycle", shadowActivity.getOptionsMenu());
    }
    */

}
