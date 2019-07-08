package jp.ac.ibaraki.japanweatherapp

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()
    private val runnable = Runnable {
        // write code that you want to delay. for example,
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.postDelayed(runnable, 1500)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}