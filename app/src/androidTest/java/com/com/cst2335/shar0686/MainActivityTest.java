package com.com.cst2335.shar0686;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("12345"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void TestFindMissingUpperCase() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("samarth123#@*"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void TestMissingSpecialChar() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("Samarth123"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You shall not pass!")));
    }
    @Test
    public void TestMissingLowerCase() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("SAMARTH123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void TestMissingNumber() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("Samarth@$*"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void TestLongPassword() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("Samarth#$*9289382uur@"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void TestComplexEnough() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword2));
        appCompatEditText.perform(replaceText("Samarth123@$*"));

        ViewInteraction materialButton = onView(withId(R.id.button4));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView2));
        textView.check(matches(withText("You meet the requirements")));
    }




    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}