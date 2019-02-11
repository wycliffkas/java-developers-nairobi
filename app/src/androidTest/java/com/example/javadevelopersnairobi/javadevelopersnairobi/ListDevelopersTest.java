package com.example.javadevelopersnairobi.javadevelopersnairobi;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.DetailActivity;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ListDevelopersTest {

	@Rule
	public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void testRecyclerViewItem_shouldHaveDeveloperData(){
		GithubUsers users = new GithubUsers(
				"k33ptoo",
				"https://github.com/k33ptoo",
				"R.drawable.user"
		);

		onView(withId(R.id.users_recyclerView))
				.check(matches(hasDeveloperDataForPosition(0, users)));
	}

	private static Matcher<View> hasDeveloperDataForPosition(final int position, @NonNull final GithubUsers githubUsers){

		return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

			@Override
			public void describeTo(Description description) {
				description.appendText("Item has user data at position" +position+ ":");
			}

			@Override
			protected boolean matchesSafely(RecyclerView recyclerView) {
				if(recyclerView == null){
					return false;
				}

				RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
				if(viewHolder == null){
					return false;
				}

				return withChild(withText(githubUsers.getUsername())).matches(viewHolder.itemView);
			}
		};
	}

}
