package com.example.javadevelopersnairobi.javadevelopersnairobi;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.UiThread;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.javadevelopersnairobi.javadevelopersnairobi.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule public ActivityTestRule<MainActivity> mActivity
			= new ActivityTestRule<>(MainActivity.class);

	@Test
	public void RecyclerViewIsVisible(){
		onView(withId(R.id.users_recyclerView)).check(matches(isDisplayed()));
	}


	@Test
	@UiThread
	public void testOnSavedInstanceState() throws Throwable {
		mActivity.launchActivity(new Intent());

		final Activity act = mActivity.getActivity();

		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				act.finish();
				act.recreate();
			}
		});
	}

	@Test
	public void recyclerViewItems() {
		onView(withId(R.id.users_recyclerView)).perform(RecyclerViewActions.scrollToPosition(1));
	}

	@Test
	public void recyclerViewItemsClick() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		onView(withId(R.id.users_recyclerView))
			.perform(RecyclerViewActions.scrollToPosition(1)).perform(click());
	}


	@Test
	public void ScreenOrientation(){
		mActivity.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		onView(withId(R.id.users_recyclerView)).perform(RecyclerViewActions.scrollToPosition(11));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mActivity.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}



}
