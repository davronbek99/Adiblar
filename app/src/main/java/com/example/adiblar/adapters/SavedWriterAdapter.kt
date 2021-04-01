package com.example.adiblar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblar.R
import com.example.adiblar.db.WriterEntity
import com.example.adiblar.models.Writer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_writer_info.view.*
import kotlinx.android.synthetic.main.item_writer.view.*

class SavedWriterAdapter(var onItemClickListener: OnItemClickListener) :
    ListAdapter<WriterEntity, SavedWriterAdapter.VH>(MyDiffUtil()) {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(writerEntity: WriterEntity) {
            Picasso.get().load(writerEntity.imgUrl).into(itemView.img_writer)
            itemView.tv_writer.text = writerEntity.name
            itemView.born_tv.text = writerEntity.born
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(writerEntity)
            }

        }

    }

    class MyDiffUtil() : DiffUtil.ItemCallback<WriterEntity>() {
        override fun areItemsTheSame(oldItem: WriterEntity, newItem: WriterEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WriterEntity, newItem: WriterEntity): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_writer, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemClickListener {
        fun onItemClick(writerEntity: WriterEntity)
    }

}