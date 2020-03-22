package com.example.app

import java.util.*

class TestUtils {

    companion object {
        fun loadResourceAsString(
            fileName: String,
            javaClass: Class<*> = TestUtils::class.java,
            charset: String = "UTF-8"
        ): String {
            val inputStream = javaClass.getResourceAsStream(fileName)
                ?: throw IllegalArgumentException("No such file")
            return Scanner(inputStream, charset).useDelimiter("\\A").next()
        }
    }
}