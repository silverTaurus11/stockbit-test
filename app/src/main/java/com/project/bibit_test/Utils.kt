package com.project.bibit_test

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat

object Utils {
    private const val PASSWORD_LENGTH_MINIMUM = 6

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.windowToken?.apply {
            inputMethodManager.hideSoftInputFromWindow(this, 0)
        }
    }

    fun isUserNameValid(username: String?) = !username.isNullOrEmpty()

    fun isUserPasswordValid(password: String?) =  password?.length?:0 >= PASSWORD_LENGTH_MINIMUM


    fun printDecimalFormat(double: Double): String{
        return DecimalFormat("##.###").format(double).replace(".",",")
    }
}