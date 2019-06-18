package ips.mobile.gitrockstars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ips.mobile.gitrockstars.databinding.ActivityMainBinding
import ips.mobile.gitrockstars.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initToolbar()
        addFragment(SearchFragment.TAG, SearchFragment.newInstance())
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp)
    }

    fun addFragment(tag: String, fragment: Fragment) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container,fragment, tag)
                .commit()
        }
    }
}
