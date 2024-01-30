package com.hsbc.jokeapplication

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.hsbc.jokeapplication.view.HomeFragment
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class HomeFragmentTest {
    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(Bundle(), androidx.appcompat.R.style.Theme_AppCompat)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun ensureTextChangesWork() {
        onView(withId(R.id.joke_limit_input))
            .perform(typeText("5"))

        // Check that the text was changed.
        onView(withId(R.id.joke_limit_input)).check(matches(withText("5")))
    }

    @Test
    fun ensureSubmitButtonWork() {
        // Create a mock NavController
        val mockNavController = mockk<NavController>()
        // Set the NavController property on the fragment
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Type text and then press the button.
        onView(withId(R.id.joke_limit_input))
            .perform(typeText("5"))
        closeSoftKeyboard()
        onView(withId(R.id.submit_button)).perform(click())

        verify { mockNavController.navigate(R.id.action_home_to_jokeFragment) }
    }

}
