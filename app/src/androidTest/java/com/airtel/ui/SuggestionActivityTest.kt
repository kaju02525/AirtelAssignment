package com.airtel.ui

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.airtel.R
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SuggestionActivityTest {
    @get:Rule
     val mSuggestionActivityTestRule = ActivityTestRule(SuggestionActivity::class.java)

      private lateinit var mSuggestionActivity:SuggestionActivity

    @Before
    fun setUp() {
        mSuggestionActivity=mSuggestionActivityTestRule.activity
    }

    @Test
    fun testCaseAPI() {
        mSuggestionActivity.setSuggestionQuery("gurgaon")
        Thread.sleep(2000)
           var expectedValue=""
          mSuggestionActivity.suggestionList.forEach {
               expectedValue=it.city!!
          }
        Assert.assertEquals("gurgaon",expectedValue)
    }

    @Test
    fun enterSearchBoxSuggestion() {
       val searchQuery="Delhi"
       val searchAnotherQuery="Patna"


        enterQuery(searchQuery)
        onView(withId(R.id.in_clear)).perform(click())
        Thread.sleep(3000)
        enterQuery(searchAnotherQuery)

    }

    fun enterQuery(query:String){
        onView(withId(R.id.search_suggest))
            .perform(typeText(query))
            .check(matches(isDisplayed()))
        closeSoftKeyboard()

        Thread.sleep(2000)
        onView(withId(R.id.suggestion_list)).perform(ScrollToBottomAction())
        Thread.sleep(3000)
    }



}