package com.mohammad.kk.findmove.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mohammad.kk.findmove.R
import com.mohammad.kk.findmove.model.MovieItem


class MovieListAdapter(private var items: ArrayList<MovieItem>, private var onClickListener:(MovieItem)-> Unit,
                       private var onLongListener:(MovieItem)-> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pictureFromMove:ShapeableImageView = itemView.findViewById(R.id.pictureFromMove)
        private val textNameMove:TextView = itemView.findViewById(R.id.textNameMove)
        private val textNameDirector:TextView = itemView.findViewById(R.id.textNameDirector)
        private val textYearOfConstruction:TextView = itemView.findViewById(R.id.textYearOfConstruction)
        private val textNameAuthor:TextView = itemView.findViewById(R.id.textNameAuthor)
        private val expandTextView:TextView = itemView.findViewById(R.id.expandTextView)
        private val clickItem:FrameLayout = itemView.findViewById(R.id.clickItem)
        val btnExpanded:ImageButton = itemView.findViewById(R.id.btnExpanded)
        fun bind(move: MovieItem){
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
        fun imgClick(items: MovieItem,onClickListener: (MovieItem) -> Unit) {
            clickItem.setOnClickListener {
                onClickListener(items)

            }
        }
        fun longClick(items: MovieItem,onLongListener: (MovieItem) -> Unit){
            clickItem.setOnLongClickListener {
                onLongListener(items)
                true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card_recycler, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = items[position]
        holder.bind(items)
        holder.btnExpanded.setOnClickListener {
            val expanded = items.isExpanded
            items.isExpanded = !expanded
            notifyItemChanged(position)
        }
        holder.imgClick(items,onClickListener)
        holder.longClick(items,onLongListener)
    }
    override fun getItemCount(): Int = items.size
}


