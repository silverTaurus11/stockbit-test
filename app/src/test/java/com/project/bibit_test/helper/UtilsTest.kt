package com.project.bibit_test.helper

import com.project.bibit_test.Utils
import org.junit.Assert
import org.junit.Test


class UtilsTest {

    @Test
    fun isUsernameValid_Success(){
        val username = "hohoho"
        val isValid = Utils.isUserNameValid(username)
        Assert.assertTrue(isValid)
    }

    @Test
    fun isUsernameValid_Failed(){
        val username = ""
        val isValid = Utils.isUserNameValid(username)
        Assert.assertFalse(isValid)
    }

    @Test
    fun isPasswordValid_Success(){
        val password = "123456780"
        val isValid = Utils.isUserPasswordValid(password)
        Assert.assertTrue(isValid)
    }

    @Test
    fun isUsernamePassword_Failed(){
        val password = "1234"
        val isValid = Utils.isUserPasswordValid(password)
        Assert.assertFalse(isValid)
    }

    @Test
    fun printDecimalFormat(){
        val value = 0.24489
        Assert.assertEquals("0,245", Utils.printDecimalFormat(value))
    }
}