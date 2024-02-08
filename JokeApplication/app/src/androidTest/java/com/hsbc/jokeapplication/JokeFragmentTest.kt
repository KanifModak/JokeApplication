package com.hsbc.jokeapplication

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.hsbc.jokeapplication.view.JokeFragment
import com.hsbc.jokeapplication.view.adapters.JokeRecyclerViewAdapter
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
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.jokeRecyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<JokeRecyclerViewAdapter.ViewHolder>(1,click()))
    }

}
