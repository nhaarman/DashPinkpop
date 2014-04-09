package com.nhaarman.dashclock.pinkpop.schedule.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.preferences.PreferencesActivity;
import com.nhaarman.dashclock.pinkpop.schedule.ScheduleActivity;

import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@SuppressWarnings({"SynchronizationOnLocalVariableOrMethodParameter", "AnonymousInnerClass", "InnerClassTooDeeplyNested", "WaitNotInLoop", "NakedNotify", "UnconditionalWait",
        "FieldAccessedSynchronizedAndUnsynchronized"})
public class ScheduleActivityTest extends ActivityInstrumentationTestCase2<ScheduleActivity> {

    private static final int DELAY_MILLIS = 300;
    private static final int WAIT_MILLIS = 5000;

    private Instrumentation mInstrumentation;
    private ScheduleActivity mActivity;

    private ViewPager mViewPager;
    private View mSaturdayFragmentHolder;

    public ScheduleActivityTest() {
        super(ScheduleActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mInstrumentation = getInstrumentation();
        mActivity = getActivity();
        mViewPager = (ViewPager) mActivity.findViewById(R.id.activity_schedule_viewpager);
        mSaturdayFragmentHolder = mActivity.findViewById(R.id.activity_schedule_saturdayfragmentholder);
    }

    public void testInitialization() {
        Assert.assertTrue(mViewPager != null ^ mSaturdayFragmentHolder != null);

        if (mViewPager != null) {
            assertThat(mViewPager.getAdapter(), is(not(nullValue())));
            assertThat(mViewPager.getAdapter().getCount(), is(equalTo(3)));
        } else {
            assertThat(mActivity.getSupportFragmentManager().findFragmentById(R.id.activity_schedule_saturdayfragmentholder), is(not(nullValue())));
            assertThat(mActivity.getSupportFragmentManager().findFragmentById(R.id.activity_schedule_sundayfragmentholder), is(not(nullValue())));
            assertThat(mActivity.getSupportFragmentManager().findFragmentById(R.id.activity_schedule_mondayfragmentholder), is(not(nullValue())));
        }
    }

    public void testStartPreferencesActivity() {
        Instrumentation.ActivityMonitor activityMonitor = mInstrumentation.addMonitor(PreferencesActivity.class.getName(), null, false);
        mInstrumentation.invokeMenuActionSync(mActivity, R.id.menu_fragment_schedule_preferences, 0);

        Activity preferencesActivity = mInstrumentation.waitForMonitorWithTimeout(activityMonitor, 5000);
        assertThat(preferencesActivity, is(not(nullValue())));
        assertThat(preferencesActivity, is(instanceOf(PreferencesActivity.class)));
        preferencesActivity.finish();
    }

    @SuppressWarnings("JUnitTestMethodWithNoAssertions")
    public void testViewPagerSelection() throws InterruptedException {
        if (mViewPager != null) {
            final Object lock = new Object();

            mInstrumentation.waitForIdleSync();

            synchronized (lock) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final Handler handler = new Handler();
                        mViewPager.setCurrentItem(1);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mViewPager.setCurrentItem(2);

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mViewPager.setCurrentItem(1);

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                mViewPager.setCurrentItem(0);

                                                synchronized (lock) {
                                                    lock.notifyAll();
                                                }
                                            }
                                        }, DELAY_MILLIS);
                                    }
                                }, DELAY_MILLIS);
                            }
                        }, DELAY_MILLIS);
                    }
                });

                lock.wait(WAIT_MILLIS);
            }
        }
    }
}
