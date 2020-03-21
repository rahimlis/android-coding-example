package com.example.app.core.platform.ui.base

import android.app.Activity
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity() {

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected fun setStatusBarColor(color: Int) {

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = color
    }

    protected fun showHomeButton(show: Boolean) {
        supportActionBar?.setHomeButtonEnabled(show)
    }

    protected fun setDisplayHomeAsUpEnabled(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    protected fun setToolbarText(@StringRes textId: Int) {
        supportActionBar?.setTitle(textId)
    }

    protected fun setToolbarText(text: String) {
        supportActionBar?.title = text
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        val view = currentFocus ?: View(this)
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    inline fun <reified T : Activity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }
}