package com.mohammad.kk.findmove

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.kk.findmove.adapter.MoveListAdapter
import com.mohammad.kk.findmove.model.MoveItem
import com.mohammad.kk.findmove.util.Extension.toFaNumbers
import com.mohammad.kk.findmove.util.RootSnackBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var moveItems: ArrayList<MoveItem> = ArrayList()
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        }
        moveRecycler.adapter = MoveListAdapter(moveItems,showSnackBar,{ moveItem: MoveItem -> createAlertDialog(moveItem)})
        moveRecycler.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        moveRecycler.setHasFixedSize(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activityExtensionMethod)
            startActivity(Intent(this,MethodExtensionActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
    private fun createAlertDialog(moveItem: MoveItem){
        val view: View = LayoutInflater.from(this).inflate(R.layout.dialog_move_full_list, null)
        val btnCloseDialog = view.findViewById<ImageButton>(R.id.btnCloseDialog)
        val imgFullMoveDialog = view.findViewById<ImageView>(R.id.imgFullMoveDialog)
        val textNameMoveDialog = view.findViewById<TextView>(R.id.textNameMoveDialog)

        val builder = AlertDialog.Builder(this)
            .setView(view)
        val dialog = builder.create()
        btnCloseDialog.setOnClickListener {
            dialog.dismiss()
        }
        imgFullMoveDialog.setBackgroundResource(moveItem.picture)
        imgFullMoveDialog.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        textNameMoveDialog.text = moveItem.name
        dialog.window!!.attributes.windowAnimations = R.style.DialogFullImage
        dialog.show()
    }
}