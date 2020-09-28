package com.mohammad.kk.findmove.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mohammad.kk.findmove.R
import com.mohammad.kk.findmove.model.MoveItem


class MoveListAdapter(private var context: Context, private var moveItems: List<MoveItem>) : RecyclerView.Adapter<MoveListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pictureFromMove:ShapeableImageView = itemView.findViewById(R.id.pictureFromMove)
        private val textNameMove:TextView = itemView.findViewById(R.id.textNameMove)
        private val textNameDirector:TextView = itemView.findViewById(R.id.textNameDirector)
        private val textYearOfConstruction:TextView = itemView.findViewById(R.id.textYearOfConstruction)
        private val textNameAuthor:TextView = itemView.findViewById(R.id.textNameAuthor)
        private val expandTextView:TextView = itemView.findViewById(R.id.expandTextView)
        val btnExpanded:ImageButton = itemView.findViewById(R.id.btnExpanded)
        fun bind(move: MoveItem){
            val expanded = move.isExpanded
            pictureFromMove.setImageResource(move.picture)
            textNameMove.text = move.name
            textNameDirector.text = move.director
            textYearOfConstruction.text = move.yearOfConstruction
            textNameAuthor.text = move.nameAuthor
            expandTextView.text = move.fullDescription
            expandTextView.maxLines = if (expanded) Int.MAX_VALUE else 3
            if (expanded) btnExpanded.setImageResource(R.drawable.ic_arrow_down) else btnExpanded.setImageResource(R.drawable.ic_arrow_up)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_move_recycler, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moveItem = moveItems[position]
        holder.bind(moveItem)
        holder.pictureFromMove.setOnClickListener {
            createAlertDialog(moveItem.picture, moveItem.name)
        }
        holder.btnExpanded.setOnClickListener {
            val expanded = moveItem.isExpanded
            moveItem.isExpanded = !expanded
            notifyItemChanged(position)
        }
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

