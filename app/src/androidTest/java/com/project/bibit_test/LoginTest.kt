package com.project.bibit_test

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.project.bibit_test.helper.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class LoginTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun tes2_login(){
        Espresso.onView(withId(R.id.username_edit_text))
                .perform(ViewActions.clearText(), ViewActions.typeText("lalalala"))
        Espresso.onView(withId(R.id.password_edit_text))
                .perform(ViewActions.clearText(), ViewActions.typeText("123456"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.login_button))
                .perform(scrollTo())
                .perform(ViewActions.click())
    }

    @Test
    fun tes1_login_failed(){
        Espresso.onView(withId(R.id.username_edit_text))
                .perform(ViewActions.clearText(), ViewActions.typeText(""))
        Espresso.onView(withId(R.id.password_edit_text))
                .perform(ViewActions.clearText(), ViewActions.typeText("12346"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.login_button))
                .perform(ViewActions.click())
        Espresso.onView(withId(R.id.username_edit_text))
                .check(ViewAssertions.matches(hasErrorText((getResourceString(R.string.username_is_required)))))
        Espresso.onView(withId(R.id.password_edit_text))
                .check(ViewAssertions.matches(hasErrorText((getResourceString(R.string.password_is_required)))))
    }

    private fun getResourceString(id: Int): String {
        return activityRule.activity.resources.getString(id)
    }


}