package com.mohammad.kk.findmove

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.kk.findmove.Helper.*
import com.mohammad.kk.findmove.adapter.MovieListAdapter
import com.mohammad.kk.findmove.model.MovieItem
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.RootSnackBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_show_poster.view.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private var items: ArrayList<MovieItem> = ArrayList()
    private val rootSnackBarProvider: RootSnackBar by inject()
    private lateinit var movieListAdapter: MovieListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        mainPosterMovie.setImageResource(R.drawable.godfather)
        items.add(
            MovieItem(
                R.drawable.godfather,
                "$name $godfatherName",
                "$nameDirector $godfatherNameDirector",
                "$yearOfConstruction $godfatherYearOfConstruction".toFaNumbers(),
                "$nameAuthor $godfatherNameAuthor",
                godfatherFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.city_of_gods,
                "$name $cityOfGodsName",
                "$nameDirector $cityOfGodsNameDirector",
                "$yearOfConstruction $cityOfGodsYearOfConstruction".toFaNumbers(),
                "$nameAuthor $cityOfGodsNameAuthor",
                cityOfGodsFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.baby_million_dollar,
                "$name $millionDollarBabyName",
                "$nameDirector $millionDollarBabyNameDirector",
                "$yearOfConstruction $millionDollarBabyYearOfConstruction".toFaNumbers(),
                "$nameAuthor $millionDollarBabyNameAuthor",
                millionDollarBabyFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.the_irishman,
                "$name $theIrishmanName",
                "$nameDirector $theIrishmanNameDirector",
                "$yearOfConstruction $theIrishmanYearOfConstruction".toFaNumbers(),
                "$nameAuthor $theIrishmanNameAuthor",
                theIrishmanFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.heat,
                "$name $heatName",
                "$nameDirector $heatNameDirector",
                "$yearOfConstruction $heatYearOfConstruction".toFaNumbers(),
                "$nameAuthor $heatNameAuthor",
                heatFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.arrival,
                "$name $arrivalName",
                "$nameDirector $arrivalNameDirector",
                "$yearOfConstruction $arrivalYearOfConstruction".toFaNumbers(),
                "$nameAuthor $arrivalNameAuthor",
                arrivalFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.the_drop,
                "$name $theDropName",
                "$nameDirector $theDropNameDirector",
                "$yearOfConstruction $theDropYearOfConstruction".toFaNumbers(),
                "$nameAuthor $theDropNameAuthor",
                theDropFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.once_upon_a_time_in_hollywood,
                "$name $onceUponATimeInHollywoodName",
                "$nameDirector $onceUponATimeInHollywoodNameDirector",
                "$yearOfConstruction $onceUponATimeInHollywoodYearOfConstruction".toFaNumbers(),
                "$nameAuthor $onceUponATimeInHollywoodNameAuthor",
                onceUponATimeInHollywoodFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.room,
                "$name $roomName",
                "$nameDirector $roomNameDirector",
                "$yearOfConstruction $roomYearOfConstruction".toFaNumbers(),
                "$nameAuthor $roomNameAuthor",
                roomFullDescription.toFaNumbers(),
            )
        )
        items.add(
            MovieItem(
                R.drawable.whiplash,
                "$name $whiplashName",
                "$nameDirector $whiplashDirector",
                "$yearOfConstruction $whiplashYearOfConstruction".toFaNumbers(),
                "$nameAuthor $whiplashNameAuthor",
                whiplashFullDescription.toFaNumbers(),
            )
        )
        val showSnackBar : (MovieItem) -> Unit = { i->
            mainPosterMovie.setImageResource(i.picture)
            val bitmap = mainPosterMovie.drawable.toBitmap()
            rootSnackBarProvider.show(this@MainActivity,i.name,true)
            mainToolbarLayout.setContentScrimColor(getColor(bitmap))
        }
        movieListAdapter = MovieListAdapter(items, showSnackBar, {
            createAlertDialog(it)
        })
        moveRecycler.adapter = movieListAdapter
        moveRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        moveRecycler.setHasFixedSize(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.extensionMethod) {
            val intent = Intent(this,MethodExtensionActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.reloadActivity) recreate()
        return super.onOptionsItemSelected(item)
    }
    private fun createAlertDialog(items: MovieItem){
        val view: View = LayoutInflater.from(this).inflate(R.layout.dialog_show_poster, null)
        val builder = AlertDialog.Builder(this)
            .setView(view)
        val dialog = builder.create()
        view.closeDialogShowPoster.setOnClickListener { dialog.dismiss() }
        view.posterMovie.setImageResource(items.picture)
        view.posterMovie.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        view.nameMovie.text = items.name
        dialog.window!!.attributes.windowAnimations = R.style.DialogZoomAnim
        dialog.window!!.setBackgroundDrawableResource(R.drawable.background_round)
        dialog.show()
    }
    private fun getColor(bitmap: Bitmap):Int {
        val defaultColor = ContextCompat.getColor(this, R.color.blueA200)
        val palette = Palette.from(bitmap).generate()
        val muted = palette.getMutedColor(defaultColor)
        return palette.getVibrantColor(muted)
    }

}