package com.project.bibit_test.helper.avatar

object StringRandomColor {

    fun getColor(text: String): String{
        if(text.length < 3) text.plus("ABC")
        return "#"+ intToARGB(text.hashCode()).slice(0..5)
    }

    private fun intToARGB(i: Int): String {
        return Integer.toHexString(i shr 24 and 0xFF) +
                Integer.toHexString(i shr 16 and 0xFF) +
                Integer.toHexString(i shr 8 and 0xFF) +
                Integer.toHexString(i and 0xFF)
    }
}