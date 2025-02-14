package com.aspirant.clicker

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aspirant.clicker.boost.BoostView


class BoostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boosts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<LinearLayout>(R.id.boosts_layout).removeAllViews()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            0, "Новый буст", 1, 100, 1
        ), "boost 0")
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            1, "Супер буст", 1, 500, 2
        ), "boost 1")
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            2, "Сигма буст", 1, 1500, 5
        ), "boost 1")
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            3, "Бубубуст", 1, 2500, 6
        ), "boost 1")
        transaction.commit()
    }
}