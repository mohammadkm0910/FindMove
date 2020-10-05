package com.mohammad.kk.findmove.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mohammad.kk.findmove.R
import com.mohammad.kk.findmove.model.MoveItem


class MoveListAdapter(private var moveItems: List<MoveItem>,private var onClickListener:(MoveItem)-> Unit,
                      private var onLongListener:(MoveItem)-> Unit) : RecyclerView.Adapter<MoveListAdapter.ViewHolder>() {
    private lateinit var context:Context
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pictureFromMove:ShapeableImageView = itemView.findViewById(R.id.pictureFromMove)
        private val textNameMove:TextView = itemView.findViewById(R.id.textNameMove)
        private val textNameDirector:TextView = itemView.findViewById(R.id.textNameDirector)
        private val textYearOfConstruction:TextView = itemView.findViewById(R.id.textYearOfConstruction)
        private val textNameAuthor:TextView = itemView.findViewById(R.id.textNameAuthor)
        private val expandTextView:TextView = itemView.findViewById(R.id.expandTextView)
        private val clickItem:FrameLayout = itemView.findViewById(R.id.clickItem)
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
        fun imgClick(moveItem: MoveItem,onClickListener: (MoveItem) -> Unit) {
            clickItem.setOnClickListener { onClickListener(moveItem) }
        }
        fun longClick(moveItem: MoveItem,onLongListener: (MoveItem) -> Unit){
            clickItem.setOnLongClickListener {
                onLongListener(moveItem)
                true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_move_card_recycler, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moveItem = moveItems[position]
        holder.bind(moveItem)
        holder.btnExpanded.setOnClickListener {
            val expanded = moveItem.isExpanded
            moveItem.isExpanded = !expanded
            notifyItemChanged(position)
        }
        holder.imgClick(moveItem,onClickListener)
        holder.longClick(moveItem,onLongListener)
    }
    override fun getItemCount(): Int {
        return moveItems.size
    }
}


