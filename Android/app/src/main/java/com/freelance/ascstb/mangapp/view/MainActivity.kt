package com.freelance.ascstb.mangapp.view

import android.annotation.TargetApi
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.freelance.ascstb.mangapp.R
import com.freelance.ascstb.mangapp.adapter.RVMangaAdapter
import com.freelance.ascstb.mangapp.model.entity.Manga
import com.freelance.ascstb.mangapp.viewmodel.LatestViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewModel: LatestViewModel
    private lateinit var adapter: RVMangaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: ")

        //region Navigation Drawer Support
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        //endregion

        initRecyclerView()
        this.viewModel = ViewModelProviders.of(this).get(LatestViewModel::class.java)
        viewModel.mangaList.observe(this, Observer { mangaList ->
            Log.d(TAG, "mangaList.observe ${mangaList!!.size}")
            adapter.updateMangaList(mangaList)
        })
        updateMangaList()
        setOnScrollEndListener()
    }

    //region Navigation Drawer
    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed: ")
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenu: ")
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected: ")
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onNavigationItemSelected: ")
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    //endregion

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ")
        val rvMangaList = findViewById<RecyclerView>(R.id.rvMangas)
        val layoutManager = GridLayoutManager(applicationContext, 3)
        //this.adapter = RVMangaAdapter(applicationContext, ArrayList<Manga>())
        this.adapter = RVMangaAdapter(ArrayList<Manga>()) { manga: Manga -> mangaClicked(manga) }
        rvMangaList.layoutManager = layoutManager
        rvMangaList.adapter = adapter
    }

    private fun updateMangaList() {
        Log.d(TAG, "updateMangaList: ")
        viewModel.updatePage()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun setOnScrollEndListener() {
        rvMangas.setOnScrollChangeListener { view, i, i1, i2, i3 ->
            if (!(view as RecyclerView).canScrollVertically(1)) {
                Log.d(TAG, "setOnScrollEndListener: ")
                updateMangaList()
            }
        }
    }

    private fun mangaClicked(manga: Manga) {
        Log.d(TAG, "mangaClicked: ${manga.title}")
        intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("TitleUrl", manga.titleUrl)
        intent.putExtra("coverUrl", manga.coverUrl)
        startActivity(intent)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName + "_TAG"
    }
}
