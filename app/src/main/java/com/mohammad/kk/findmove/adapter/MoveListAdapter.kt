package com.mohammad.kk.findmove.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mohammad.kk.findmove.R
import com.mohammad.kk.findmove.model.MoveItem


class MoveListAdapter(private var context: Context, private var moveItems: List<MoveItem>) : RecyclerView.Adapter<MoveListAdapter.ViewHolder>() {
    private val lastPosition = -1
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pictureFromMove:ShapeableImageView = itemView.findViewById(R.id.pictureFromMove)
        var textNameMove:TextView = itemView.findViewById(R.id.textNameMove)
        var textNameDirector:TextView = itemView.findViewById(R.id.textNameDirector)
        var textYearOfConstruction:TextView = itemView.findViewById(R.id.textYearOfConstruction)
        var textNameAuthor:TextView = itemView.findViewById(R.id.textNameAuthor)
        var textShortDescription:TextView = itemView.findViewById(R.id.textShortDescription)
        var btnExpandableButton:ImageButton = itemView.findViewById(R.id.btnExpandableButton)
        var textFullDescription:TextView = itemView.findViewById(R.id.textFullDescription)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_move_recycler, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moveItem = moveItems[position]
        holder.pictureFromMove.setImageResource(moveItem.picture)
        holder.textNameMove.text = moveItem.name
        holder.textNameDirector.text = moveItem.director
        holder.textYearOfConstruction.text = moveItem.yearOfConstruction
        holder.textNameAuthor.text = moveItem.nameAuthor
        holder.textShortDescription.text = moveItem.shortDescription
        holder.pictureFromMove.setOnClickListener {
            createAlertDialog(moveItem.picture, moveItem.name)
        }
        holder.btnExpandableButton.setOnClickListener { v->
            if (!moveItem.setExtExpandable){
                holder.textFullDescription.visibility = View.VISIBLE
                v.animate().rotation(180f).setDuration(500).start()
                moveItem.setExtExpandable = true
            } else {
                holder.textFullDescription.visibility = View.GONE
                v.animate().rotation(0f).setDuration(500).start()
                moveItem.setExtExpandable = false
            }
        }
        holder.textFullDescription
        holder.textFullDescription.text = moveItem.fullDescription
    }
    override fun getItemCount(): Int {
        return moveItems.size
    }
    @SuppressLint("InflateParams")
    private fun createAlertDialog(img: Int, title: String){
        val view:View = LayoutInflater.from(context).inflate(R.layout.dialog_move_full_list, null)
        val btnCloseDialog = view.findViewById<ImageButton>(R.id.btnCloseDialog)
        val imgFullMoveDialog = view.findViewById<ImageView>(R.id.imgFullMoveDialog)
        val textNameMoveDialog = view.findViewById<TextView>(R.id.textNameMoveDialog)
        val builder = AlertDialog.Builder(context)
            .setView(view)
        val dialog = builder.create()
        btnCloseDialog.setOnClickListener {
            dialog.dismiss()
        }
        imgFullMoveDialog.setBackgroundResource(img)
        imgFullMoveDialog.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
        textNameMoveDialog.text = title
        dialog.window!!.attributes.windowAnimations = R.style.DialogFullImage
        dialog.show()
    }

}