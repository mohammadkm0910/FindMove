package com.mohammad.kk.findmove

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.kk.findmove.adapter.MoveListAdapter
import com.mohammad.kk.findmove.model.MoveItem
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.RootSnackBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_show_poster.*
import kotlinx.android.synthetic.main.dialog_show_poster.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private var moveItems: ArrayList<MoveItem> = ArrayList()
    private var counter = 0
    private var color = 0
    private lateinit var moveListAdapter: MoveListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarApp)
        moveItems.add(
            MoveItem(
                R.drawable.godfather,
                "${HelperMoves.name} ${HelperMoves.godfatherName}",
                "${HelperMoves.nameDirector} ${HelperMoves.godfatherNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.godfatherYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.godfatherNameAuthor}",
                HelperMoves.godfatherFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.city_of_gods,
                "${HelperMoves.name} ${HelperMoves.cityOfGodsName}",
                "${HelperMoves.nameDirector} ${HelperMoves.cityOfGodsNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.cityOfGodsYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.cityOfGodsNameAuthor}",
                HelperMoves.cityOfGodsFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.baby_million_dollar,
                "${HelperMoves.name} ${HelperMoves.millionDollarBabyName}",
                "${HelperMoves.nameDirector} ${HelperMoves.millionDollarBabyNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.millionDollarBabyYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.millionDollarBabyNameAuthor}",
                HelperMoves.millionDollarBabyFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.the_irishman,
                "${HelperMoves.name} ${HelperMoves.theIrishmanName}",
                "${HelperMoves.nameDirector} ${HelperMoves.theIrishmanNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.theIrishmanYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.theIrishmanNameAuthor}",
                HelperMoves.theIrishmanFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.heat,
                "${HelperMoves.name} ${HelperMoves.heatName}",
                "${HelperMoves.nameDirector} ${HelperMoves.heatNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.heatYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.heatNameAuthor}",
                HelperMoves.heatFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.arrival,
                "${HelperMoves.name} ${HelperMoves.arrivalName}",
                "${HelperMoves.nameDirector} ${HelperMoves.arrivalNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.arrivalYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.arrivalNameAuthor}",
                HelperMoves.arrivalFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.the_drop,
                "${HelperMoves.name} ${HelperMoves.theDropName}",
                "${HelperMoves.nameDirector} ${HelperMoves.theDropNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.theDropYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.theDropNameAuthor}",
                HelperMoves.theDropFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.once_upon_a_time_in_hollywood,
                "${HelperMoves.name} ${HelperMoves.onceUponATimeInHollywoodName}",
                "${HelperMoves.nameDirector} ${HelperMoves.onceUponATimeInHollywoodNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.onceUponATimeInHollywoodYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.onceUponATimeInHollywoodNameAuthor}",
                HelperMoves.onceUponATimeInHollywoodFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.room,
                "${HelperMoves.name} ${HelperMoves.roomName}",
                "${HelperMoves.nameDirector} ${HelperMoves.roomNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.roomYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.roomNameAuthor}",
                HelperMoves.roomFullDescription.toFaNumbers(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.whiplash,
                "${HelperMoves.name} ${HelperMoves.whiplashName}",
                "${HelperMoves.nameDirector} ${HelperMoves.whiplashDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.whiplashYearOfConstruction}".toFaNumbers(),
                "${HelperMoves.nameAuthor} ${HelperMoves.whiplashNameAuthor}",
                HelperMoves.whiplashFullDescription.toFaNumbers(),
            )
        )
        val showSnackBar : (MoveItem) -> Unit = {i->
            counter++
            if (counter > 6) counter = 0
            RootSnackBar(this,i.name,counter).show()
            toolbarApp.setBackgroundColor(color)
            createAlertDialog(i)
        }
        moveListAdapter = MoveListAdapter(moveItems,showSnackBar,{ moveItem: MoveItem -> createAlertDialog(moveItem,true)})
        moveRecycler.adapter = moveListAdapter
        moveRecycler.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        moveRecycler.setHasFixedSize(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activityExtensionMethod) startActivity(Intent(this,MethodExtensionActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
    private fun createAlertDialog(moveItem: MoveItem,isShow:Boolean = false){
        val view: View = LayoutInflater.from(this).inflate(R.layout.dialog_show_poster, null)
        val builder = AlertDialog.Builder(this)
            .setView(view)
        val dialog = builder.create()
        view.closeDialogShowPoster.setOnClickListener { dialog.dismiss() }
        view.imagePosterMove.setImageResource(moveItem.picture)
        view.imagePosterMove.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        view.textNameMoveDialog.text = moveItem.name
        dialog.window!!.attributes.windowAnimations = R.style.DialogFullImage
        dialog.window!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.background_round))
        color = getColor(view.imagePosterMove.drawable.toBitmap())
        toolbarApp.setBackgroundColor(getColor(view.imagePosterMove.drawable.toBitmap()))
        if (isShow) dialog.show()
    }
    private fun getColor(bitmap: Bitmap):Int {
        val defaultColor = ContextCompat.getColor(this,R.color.blueA200)
        val palette = Palette.from(bitmap).generate()
        val muted = palette.getMutedColor(defaultColor)
        return palette.getVibrantColor(muted)
    }
}