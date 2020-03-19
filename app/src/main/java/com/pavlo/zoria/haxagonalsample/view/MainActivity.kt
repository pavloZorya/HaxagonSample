package com.pavlo.zoria.haxagonalsample.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.pavlo.zoria.haxagonalsample.R
import com.pavlo.zoria.haxagonalsample.view.user.UsersListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container,
                UsersListFragment(), null
            ).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }
}
