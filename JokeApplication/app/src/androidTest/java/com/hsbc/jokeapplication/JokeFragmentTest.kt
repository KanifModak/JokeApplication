package com.hsbc.jokeapplication

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.hsbc.jokeapplication.data.viewmodels.JokeViewModel
import com.hsbc.jokeapplication.view.HomeFragment
import com.hsbc.jokeapplication.view.JokeFragment
import com.hsbc.jokeapplication.view.adapters.JokeRecyclerViewAdapter
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class JokeFragmentTest {
    private lateinit var scenario: FragmentScenario<JokeFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(Bundle(), androidx.appcompat.R.style.Theme_AppCompat)
        scenario.moveToState(Lifecycle.State.RESUMED)
//          val viewModel = ViewModelProviders.of(InstrumentationRegistry.getInstrumentation().getTargetContext(), ViewModelFactoryTest.ViewModelFactoryOfFactory.INSTANCE)
//            .get(JokeViewModel::class.java)
    }

    @Test
    fun jokeListItemClick() {
        onView(withId(R.id.jokeRecyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<JokeRecyclerViewAdapter.ViewHolder>(1,click()))
    }

}
