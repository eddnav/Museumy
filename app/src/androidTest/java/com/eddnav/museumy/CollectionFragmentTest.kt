package com.eddnav.museumy

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eddnav.museumy.feature.collection.CollectionEntryItemView
import com.eddnav.museumy.feature.collection.CollectionFragment
import com.eddnav.museumy.feature.collection.CollectionItemAdapter
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CollectionFragmentTest {

    @Test
    fun collectionFragment_testNavigationToArtworkFragment() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val collectionFragmentScenario = launchFragmentInContainer<CollectionFragment>(
            themeResId = R.style.Theme_Museumy
        )
        collectionFragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(ViewMatchers.withId(R.id.collectionList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CollectionItemAdapter.CollectionItemViewHolder>(
                    FIRST_COLLECTION_ITEM_VIEW_INDEX,
                    click()
                )
            )
        Assert.assertEquals(navController.currentDestination?.id, R.id.artworkFragment)
    }

    companion object {
        const val FIRST_COLLECTION_ITEM_VIEW_INDEX = 1
    }
}