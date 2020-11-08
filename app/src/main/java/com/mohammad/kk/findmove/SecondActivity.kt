package com.mohammad.kk.findmove

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mohammad.kk.findmove.adapter.ViewPagerMethodAdapter
import com.mohammad.kk.findmove.fragment.MethodImageFragment
import com.mohammad.kk.findmove.fragment.MethodTextFragment
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.toolbar.*

class SecondActivity : AppCompatActivity() {
    private lateinit var viewPagerMethodAdapter: ViewPagerMethodAdapter
    private val tabsIcons = arrayOf(R.drawable.ic_text, R.drawable.ic_image)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbarApp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        viewPagerMethodAdapter = ViewPagerMethodAdapter(supportFragmentManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    120
                )
            }
        }
        setupViewPager()
        setupTab()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home){
            onBackPressed()
            true
        } else
            super.onOptionsItemSelected(item)
    }
    private fun setupViewPager() {
        viewPagerMethodAdapter.addFragment(MethodTextFragment(), "توابع متنی")
        viewPagerMethodAdapter.addFragment(MethodImageFragment(), "توابع تصویری")
        viewPagerMethod.adapter = viewPagerMethodAdapter
    }
    private fun setupTab() {
        tabLayoutMethod.setupWithViewPager(viewPagerMethod)
        tabLayoutMethod.getTabAt(0)?.setIcon(tabsIcons[0])
        tabLayoutMethod.getTabAt(1)?.setIcon(tabsIcons[1])
    }

}