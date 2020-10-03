package com.mohammad.kk.findmove

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.kk.findmove.HelperMoves.toNumberFA
import com.mohammad.kk.findmove.adapter.MoveListAdapter
import com.mohammad.kk.findmove.model.MoveItem
import com.mohammad.kk.findmove.util.MyToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var moveItems: ArrayList<MoveItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moveItems.add(
            MoveItem(
                R.drawable.godfather,
                "${HelperMoves.name} ${HelperMoves.godfatherName}",
                "${HelperMoves.nameDirector} ${HelperMoves.godfatherNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.godfatherYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.godfatherNameAuthor}",
                HelperMoves.godfatherFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.city_of_gods,
                "${HelperMoves.name} ${HelperMoves.cityOfGodsName}",
                "${HelperMoves.nameDirector} ${HelperMoves.cityOfGodsNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.cityOfGodsYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.cityOfGodsNameAuthor}",
                HelperMoves.cityOfGodsFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.baby_million_dollar,
                "${HelperMoves.name} ${HelperMoves.millionDollarBabyName}",
                "${HelperMoves.nameDirector} ${HelperMoves.millionDollarBabyNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.millionDollarBabyYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.millionDollarBabyNameAuthor}",
                HelperMoves.millionDollarBabyFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.the_irishman,
                "${HelperMoves.name} ${HelperMoves.theIrishmanName}",
                "${HelperMoves.nameDirector} ${HelperMoves.theIrishmanNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.theIrishmanYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.theIrishmanNameAuthor}",
                HelperMoves.theIrishmanFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.heat,
                "${HelperMoves.name} ${HelperMoves.heatName}",
                "${HelperMoves.nameDirector} ${HelperMoves.heatNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.heatYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.heatNameAuthor}",
                HelperMoves.heatFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.arrival,
                "${HelperMoves.name} ${HelperMoves.arrivalName}",
                "${HelperMoves.nameDirector} ${HelperMoves.arrivalNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.arrivalYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.arrivalNameAuthor}",
                HelperMoves.arrivalFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.the_drop,
                "${HelperMoves.name} ${HelperMoves.theDropName}",
                "${HelperMoves.nameDirector} ${HelperMoves.theDropNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.theDropYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.theDropNameAuthor}",
                HelperMoves.theDropFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.once_upon_a_time_in_hollywood,
                "${HelperMoves.name} ${HelperMoves.onceUponATimeInHollywoodName}",
                "${HelperMoves.nameDirector} ${HelperMoves.onceUponATimeInHollywoodNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.onceUponATimeInHollywoodYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.onceUponATimeInHollywoodNameAuthor}",
                HelperMoves.onceUponATimeInHollywoodFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.room,
                "${HelperMoves.name} ${HelperMoves.roomName}",
                "${HelperMoves.nameDirector} ${HelperMoves.roomNameDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.roomYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.roomNameAuthor}",
                HelperMoves.roomFullDescription.toNumberFA(),
            )
        )
        moveItems.add(
            MoveItem(
                R.drawable.whiplash,
                "${HelperMoves.name} ${HelperMoves.whiplashName}",
                "${HelperMoves.nameDirector} ${HelperMoves.whiplashDirector}",
                "${HelperMoves.yearOfConstruction} ${HelperMoves.whiplashYearOfConstruction}".toNumberFA(),
                "${HelperMoves.nameAuthor} ${HelperMoves.whiplashNameAuthor}",
                HelperMoves.whiplashFullDescription.toNumberFA(),
            )
        )
        moveRecycler.adapter = MoveListAdapter(moveItems,{ moveItem: MoveItem -> showToast(moveItem)},{ moveItem: MoveItem -> createAlertDialog(moveItem)})
        moveRecycler.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        moveRecycler.setHasFixedSize(true)
    }
    private fun showToast(moveItem: MoveItem) {
        MyToast(this,moveItem.name).show()
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