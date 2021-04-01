package com.example.adiblar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblar.R
import com.example.adiblar.db.AppDatabase
import com.example.adiblar.db.WriterEntity
import com.example.adiblar.models.Writer
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_writer.view.*

class ImageAdapter(var list: ArrayList<Writer>, var onClick: OnClickListener) :
    RecyclerView.Adapter<ImageAdapter.VH>() {


    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var writerEntity: WriterEntity
        val writerDao = AppDatabase.getDatabase(itemView.context).writerDao()
        fun onBind(writer: Writer) {
            Picasso.get().load(writer.img).into(itemView.img_writer)
            itemView.tv_writer.text = writer.name
            itemView.born_tv.text = writer.born
            writerEntity = WriterEntity()
            writerEntity.id = writer.id
            writerEntity.imgUrl = writer.img
            writerEntity.name = writer.name
            writerEntity.born = writer.born
            writerEntity.desc = writer.desc
            itemView.like_btn.isLiked = writerDao.getImageById(writerEntity.id!!)
            itemView.like_btn.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    writerDao.insertWriter(writerEntity)
                    itemView.like_btn.setBackgroundResource(R.drawable.item_background)

                    Toast.makeText(itemView.context, "Liked", Toast.LENGTH_SHORT).show()
                }

                override fun unLiked(likeButton: LikeButton?) {
                    Toast.makeText(itemView.context, "UnLiked", Toast.LENGTH_SHORT).show()
                    writerDao.deleteImage(writerEntity)
                    itemView.like_btn.setBackgroundResource(R.drawable.back_info_unlike)
                }

            })
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_writer, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener() {
            onClick.onClickWriter(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnClickListener {
        fun onClickWriter(writer: Writer)
    }
}