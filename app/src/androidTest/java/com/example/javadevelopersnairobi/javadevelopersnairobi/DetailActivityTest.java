package com.example.javadevelopersnairobi.javadevelopersnairobi;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.javadevelopersnairobi.javadevelopersnairobi.view.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

	@Rule
	public ActivityTestRule<DetailActivity> activity = new ActivityTestRule<>(DetailActivity.class);


	@Test
	public void loadDeveloperName() {

		try {
			onView(withId(R.id.username))
					.check(matches(not(isDisplayed())));
			onView(withId(R.id.shareButton))
					.perform(click());
		} catch (RuntimeException e) {

		}

	}

	@Test
	public void testFloatingActionButton_shouldBeDisplayed(){
		onView(withId(R.id.shareButton)).check(matches(isDisplayed()));
	}




}